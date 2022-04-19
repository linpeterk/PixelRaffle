package com.revature.pixelraffle.database.datamodel

import java.util.*

data class Raffle(
    val id: Int,
    val date: String,
    val RaffleName: String,
    val boardImage: Int,
    val ticketNumber: Int

)
