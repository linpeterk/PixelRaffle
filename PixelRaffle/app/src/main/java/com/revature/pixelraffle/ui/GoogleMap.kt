package com.revature.pixelraffle.ui

import android.content.ContentValues.TAG
import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.google.android.gms.dynamic.IObjectWrapper
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.gson.annotations.SerializedName
import com.google.maps.android.compose.*
import com.revature.pixelraffle.ui.screens.peter.yourInterest
import com.revature.pixelraffle.viewmodel.TheViewModel
import com.revature.pixelraffle.viewmodel.UserViewModel

//var cameraPositionState:CameraPositionState?=null

val caliMuseum = LatLng(34.05, -118.24)
val toyDistrict = LatLng(34.047, -118.243)
val brew = LatLng(34.051, -118.234)
val dodgerS = LatLng(34.073, -118.241)
val church = LatLng(34.05693923331048, -118.23957346932366)
var lat:Double = 37.4198
var lng:Double = -122.0788
val googleHQ = LatLng(lat, lng)

val googlePosition =    CameraPosition.fromLatLngZoom(
    LatLng(37.4198,  -122.0788), 14f)

var cameraPositionState:CameraPositionState =  CameraPositionState(position = CameraPosition.fromLatLngZoom(googleHQ, 14f))

/*display respective board's markers
 * mode == 1 is job board, mode==2 is skill board, mode == 3 NO marker
 *
 * on any list updates
 * */

@Composable
fun MakeGoogleMap(
    makeMarker: Boolean = false,
    vModel: UserViewModel,
    mode:Int = 0,
    navController: NavController
) {
    cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(googleHQ, 14f)
    }


    var uiSettings by remember {
        mutableStateOf(
            MapUiSettings(
                compassEnabled = true,
                myLocationButtonEnabled = true,
                mapToolbarEnabled = true
            )
        )
    }

    var properties by remember {
        mutableStateOf(MapProperties(mapType = MapType.NORMAL))
    }



    Box(Modifier.fillMaxSize())
    {
        GoogleMap(
            modifier = Modifier.matchParentSize(),
            cameraPositionState = cameraPositionState!!,
            uiSettings = uiSettings,
            properties= properties,


            onPOIClick = {
                Log.d(TAG, "POI clicked: ${it.name}")
            },

            onMapLongClick = {
//
//            cameraPositionState!!.position =
//                CameraPosition.fromLatLngZoom(
//                    userCurrentLocation,
//                    15f
//                )


            }
        ){
            Log.d("Big map", "Big Map")
            //default mode 0, don't create markers
            if(mode!=0) {
                if(mode==1) {
                    createMarkers(vModel, mode = mode, navController=navController)
                }
                else if(mode==2){
                    createMarkers(vModel, mode = mode, navController=navController)
                }
            }
        }
//        Switch(
//            checked = uiSettings.mapToolbarEnabled,
//            onCheckedChange = {
//                uiSettings = uiSettings.copy(mapToolbarEnabled = it)
//            }
//        )


//    LaunchedEffect(){
//
//    }

    }
}

@Composable
fun createMarkers(vModel: UserViewModel, mode:Int, navController:NavController){

    /*mode == 1 is job board, mode==2 is skill board, display respective board's markers
    * on any list updates
    * */
    Log.d("Map was here", "Map is here")
    Marker(
        position = googleHQ,
        title = "You",
        snippet = "You are here",
        icon= BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)
    )


    //NEED TO BE IMPLEMENTED
    if(mode == 1 ) {
        var list= vModel.yourList

    repeat(list?.count()!!) { index ->

        Marker(
            position = LatLng(
                list[index].lat ?: googleHQ.latitude,
                list[index].lng ?: googleHQ.latitude
            ), //LatLng(list[it].lat, list[it].lng),
            title = list[index].name,
            snippet = list[index].description
        )


        }
    }
/*
    else if(mode==2) {
        var list=   vModel.currentSkillList
        Marker(
            position = googleHQ,
            title = "You",
            snippet = "You are here",
            icon= BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)

        )

        repeat(list.count()){ index->

            Marker(
                position = LatLng(list[index].lat, list[index].lng),
                title = list[index].name,
                snippet = list[index].description,

                )

        }
    }
    else if(mode==3) {
        var list=   vModel.currentSkillList
        Marker(
            position = googleHQ,
            title = "You",
            snippet = "You are here",
            icon= BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)

        )


    }

     */




}