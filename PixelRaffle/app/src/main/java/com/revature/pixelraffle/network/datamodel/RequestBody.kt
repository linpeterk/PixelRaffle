package com.revature.pixelraffle.network.datamodel

import com.google.gson.annotations.SerializedName

data class GetBoardState(
    @SerializedName("boardID")
    val boardState:Int

)

data class GetGiGCategory(

    @SerializedName("personID")
    val personID: Int,

    )

