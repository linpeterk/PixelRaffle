package com.revature.pixelraffle.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.revature.pixelraffle.database.datamodel.AppDataBase
import com.revature.pixelraffle.database.datamodel.UserRow
import com.revature.pixelraffle.database.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserViewModel(application: Application): AndroidViewModel(application) {
    val getAllUsersData: LiveData<List<UserRow>>
    val getUserFirstName: LiveData<UserRow>
    private val userRepository: UserRepository

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
}

