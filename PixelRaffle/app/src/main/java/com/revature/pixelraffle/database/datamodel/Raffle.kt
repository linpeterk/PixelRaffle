package com.revature.pixelraffle.database.datamodel

data class Raffle(
    val id:Int,
    val name:String,
    val ticketNumber:String,
    val thedate : String,
    val boardImageRes:Int

)
