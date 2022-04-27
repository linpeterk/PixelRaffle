package com.revature.pixelraffle.database.datamodel

data class CurrentRaffle(
    val id:Int,
    val name:String,
    val ticketNumber:String,
    val thedate : String,
    val boardImageRes:Int

)