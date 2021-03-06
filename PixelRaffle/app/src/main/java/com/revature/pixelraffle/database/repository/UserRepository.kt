package com.revature.pixelraffle.database.repository

import android.app.Application
import androidx.lifecycle.LiveData
import com.revature.pixelraffle.database.dao.UserDao
import com.revature.pixelraffle.database.datamodel.AppDataBase
import com.revature.pixelraffle.database.datamodel.UserRow

class UserRepository(private val userDao: UserDao) {

    val getAllUser: LiveData<List<UserRow>> = userDao.getAllUSers()
    val getUserFirstName: LiveData<UserRow> = userDao.getUserFirstName()
    //Insert New User to User Table
    suspend fun addNewUser(newUser: UserRow){
        userDao.insertNewUser(newUser)
    }

    //Update current user
    suspend fun updateUser(item: UserRow){

    }

    //get user
    suspend fun getUserById(email: String) : UserRow{
      return  userDao.getUserById(email)
    }

    ////Delete User
    suspend fun deleteUser(item: UserRow){

    }

    //Delete All Users
    suspend fun deleteAllUsers(){

    }
}