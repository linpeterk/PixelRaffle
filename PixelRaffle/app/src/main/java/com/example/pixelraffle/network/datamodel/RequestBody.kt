package com.example.pixelraffle.network.datamodel

import com.google.gson.annotations.SerializedName

data class GetBoardState(
    @SerializedName("board")
    val boardState:Int

)