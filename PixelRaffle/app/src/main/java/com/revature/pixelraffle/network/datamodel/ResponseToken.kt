package com.revature.pixelraffle.network.datamodel

import com.google.gson.annotations.SerializedName

data class ResponseTokenTemp(

    @SerializedName("skills1")
    val list:List<tempResponseType>,

    )

data class ResponseGig(
    @SerializedName("yourInterest")
    val ur:List<ResponseGigType>

)

//data class ResponseBoard(
//    @SerializedName("yourBoard")
//    val yourBoard:List<ResponseBoardType>
//
//)

data class ResponseBoard(
    @SerializedName("yourBoard")
    val yourBoard:List<ResponsePlayerType>? = null

)