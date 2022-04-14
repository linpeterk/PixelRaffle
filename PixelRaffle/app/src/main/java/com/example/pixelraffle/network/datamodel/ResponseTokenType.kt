package com.example.pixelraffle.network.datamodel

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