package com.revature.pixelraffle.viewmodel

import android.app.Application
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.google.android.gms.maps.model.LatLng
import com.revature.pixelraffle.database.datamodel.AppDataBase
import com.revature.pixelraffle.database.datamodel.UserRow
import com.revature.pixelraffle.database.repository.UserRepository
import com.revature.pixelraffle.network.datamodel.GetGiGCategory
import com.revature.pixelraffle.network.datamodel.ResponseGig
import com.revature.pixelraffle.network.datamodel.ResponseGigType
import com.revature.pixelraffle.network.repository.RetrofitHelper
import com.revature.pixelraffle.ui.googleHQ
import com.revature.pixelraffle.ui.screens.peter.baseInterests
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response

class UserViewModel(application: Application): AndroidViewModel(application) {
    val getAllUsersData: LiveData<List<UserRow>>
    val getUserFirstName: LiveData<UserRow>
    private val userRepository: UserRepository

    //val currentUser:UserRow

    var currentUser : UserRow by  mutableStateOf(UserRow(first_name=",", last_name = "",email = "", password = ""))

    init{
        val userDao = AppDataBase.getDatabase(application).user_Dao()
        userRepository = UserRepository(userDao)
        getAllUsersData = userRepository.getAllUser
        getUserFirstName = userRepository.getUserFirstName
    }

    fun inserNewUser(newUser: UserRow){
        viewModelScope.launch(Dispatchers.IO){
            userRepository.addNewUser(newUser)
        }
    }

    fun getAllUserLis():LiveData<List<UserRow>>{
        return   userRepository.getAllUser
    }


    fun getUserFirstName():LiveData<UserRow>{
        return   userRepository.getUserFirstName
    }

    fun getUserByEmail(email:String) {

        viewModelScope.launch(Dispatchers.IO){
            currentUser =  userRepository.getUserById(email)
        }

    }

    //Find distance in miles between two LatLng points, 2 digits
    fun distance(start: LatLng, dest: LatLng) : Double {
        val lat1 = start.latitude
        val lat2 = dest.latitude
        val lon1 = start.longitude
        val lon2 = dest.longitude

        var p = 0.017453292519943295;    // Math.PI / 180

        var a = 0.5 - Math.cos((lat2 - lat1) * p)/2 +
                Math.cos(lat1 * p) * Math.cos(lat2 * p) *
                (1 - Math.cos((lon2 - lon1) * p))/2;
        val rounded = String.format("%.3f", 0.62137 * 12742 * Math.asin(Math.sqrt(a))) // rounds to 3 decimal places
        return rounded.toDouble() ; // 2 * R; R = 6371 km
    }

    /* sort modes by order
    *  0 = no sort, recall database
    *  1 = sort by distance
    *  2 = sort by $
    *  3 = sort by date
    * */
    fun sortBy(index:Int){

        when (index)
        {
            0-> sortDefault()
            1-> sortDist()
            2-> sortDate()
        }


    }



    fun sortDefault(){
        getGigLists()
    }


    fun sortDist(){

        val newList =  yourList.sortedWith(
            compareBy {
                var distance = distance(googleHQ,
                    LatLng(it.lat?: googleHQ.latitude, it.lng?: googleHQ.longitude)
                )
                distance}
        )

        yourList = newList

        //   distance(googleHQ,LatLng(item.lat?: googleHQ.latitude, item.lng?: googleHQ.longitude))

    }

    fun sortDate(){

        val newList =  yourList.sortedWith(
            compareBy {
                it.time}
        )

        yourList = newList

        //   distance(googleHQ,LatLng(item.lat?: googleHQ.latitude, item.lng?: googleHQ.longitude))

    }





    ///API
    val authService = RetrofitHelper.getAuthService()
    var temp = mutableStateOf(ResponseGig(listOf()))

    var yourList: List<ResponseGigType> by mutableStateOf(listOf(ResponseGigType()))
    fun getGigLists() {

        viewModelScope.launch (Dispatchers.IO) {
            try {
                val responseService: Response<ResponseGig>
                responseService = authService.getGigs(GetGiGCategory(5))

                if(responseService.isSuccessful){
                    responseService.body()?.let{

                        Log.d("Logging success", "Response token $it")
                        temp.value = it
                    }
                } else{
                    responseService.errorBody()?.let{

                        Log.d("Logging error", "response token $it")
                        it.close()
                    }
                }


            }catch(e:Exception){
                Log.d("Network logging", "Exceptions in networking Displaying Old Data$e")

            }

        }
       // return temp.value

        yourList = temp.value.ur
    }
}

