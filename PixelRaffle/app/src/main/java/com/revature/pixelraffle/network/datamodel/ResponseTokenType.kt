package com.revature.pixelraffle.network.datamodel

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import com.google.gson.annotations.SerializedName
import com.revature.pixelraffle.ui.screens.adama.ui.ui.theme.Purple200


data class tempResponseType(
    @SerializedName("catID")
    var id:Int = 0,
    @SerializedName("name")
    var name:String = "",
    @SerializedName("money")
    var money:String = "",
    @SerializedName("imageRes")
    var imageRes:String = "",

    )

data class ResponseGigType(
    @SerializedName("catID")
    var id:Int = 0,
    @SerializedName("name")
    var name:String = "",
    @SerializedName("description")
    var description:String = "",
    @SerializedName("imageRes")
    var imageRes:String = "",
    @SerializedName("time")
    var time:String = "",
    @SerializedName("address")
    var address:String = "",
    @SerializedName("lat")
    var lat:Double = 37.4198,
    @SerializedName("lng")
    var lng:Double = -122.0788,

    )

data class ResponsePlayerType(
    @SerializedName("name")
    var name:String = "",
    @SerializedName("playList")
    val playList:List<ResponseBoardType>? = null

    )

data class ResponseBoardType(
    @SerializedName("color")
    var color: Color = Purple200,
    @SerializedName("offset")
    var offset: Offset = Offset(0f,0f),

    )