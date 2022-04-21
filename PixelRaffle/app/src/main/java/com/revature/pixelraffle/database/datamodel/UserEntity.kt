package com.revature.pixelraffle.database.datamodel

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "USER_TABLE")
data class UserRow(
    @PrimaryKey(autoGenerate = true)
    val id:Int = 0,

    @ColumnInfo(name ="first_name")
    val first_name: String,

    @ColumnInfo(name ="last_name")
    val last_name: String,

    @ColumnInfo(name ="email")
    val email: String,

    //@ColumnInfo(name ="secret_questions")
    //val secret_questions: String,

    //@ColumnInfo(name ="secret_answer")
    //val secret_answer: String,

    @ColumnInfo(name ="password")
    val password: String,

    )