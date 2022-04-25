package com.revature.pixelraffle.database.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.revature.pixelraffle.database.datamodel.UserRow

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
   suspend fun insertNewUser(newUser: UserRow)

   @Query("SELECT * FROM USER_TABLE")
   fun getAllUSers(): LiveData<List<UserRow>>

   @Query("SELECT * FROM USER_TABLE WHERE id = :id")
   fun getUserById(id: Int): UserRow

   @Query("SELECT * FROM USER_TABLE")
   fun getUserFirstName(): LiveData<UserRow>

   //@Update
   //suspend fun updateUser(item: UserRow)

   //@Delete
   //suspend fun deleteUser(item: UserRow)

  // @Delete
  // suspend fun deleteAllUser(): LiveData<UserRow>
}