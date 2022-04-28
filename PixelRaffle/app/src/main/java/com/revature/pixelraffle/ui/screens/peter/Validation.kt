package com.revature.pixelraffle.ui.screens.peter

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.revature.pixelraffle.data.Player
import kotlin.math.roundToInt

var rnds_x: MutableState<Int?> = mutableStateOf(null)
var rnds_y: MutableState<Int?> = mutableStateOf(null)

fun  validation():Boolean{
    var winner = false
    rnds_x.value = (0..1000).random()
    rnds_y.value = (0..1000).random()
    Log.d("Rolling", "$rnds_x,$rnds_y")
    Player.list.forEach { it->
        if( it.offset.x.roundToInt() == rnds_x.value && it.offset.x.roundToInt() == rnds_y.value)
            winner = true
    }
    return winner
}