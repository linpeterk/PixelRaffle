package com.revature.pixelraffle.data

import androidx.compose.runtime.mutableStateListOf

object Player {

    val maps = HashMap<String, Boolean>()
    // val list = mutableStateListOf<Pair>()
    val list = mutableStateListOf<OffsetColor>()
    val dragList = mutableStateListOf<OffsetColor>()

}