package com.revature.pixelraffle.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface UserDao {
    //@Insert(onConflict = OnConflictStrategy.IGNORE)
   // suspend fun intertUser(newUser: UserRow)

   // @Query("SELECT * FROM Users")
}