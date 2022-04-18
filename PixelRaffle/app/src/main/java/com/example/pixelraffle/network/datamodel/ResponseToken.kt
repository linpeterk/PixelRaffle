package com.example.pixelraffle.network.datamodel

import com.google.gson.annotations.SerializedName

data class ResponseTokenTemp(



    @SerializedName("skills1")
    val list:List<tempResponseType>,

    )