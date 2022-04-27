package com.revature.pixelraffle.network.datamodel

import com.google.gson.annotations.SerializedName


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
