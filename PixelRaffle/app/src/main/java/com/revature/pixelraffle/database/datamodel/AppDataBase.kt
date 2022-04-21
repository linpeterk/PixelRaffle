package com.revature.pixelraffle.database.datamodel

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.revature.pixelraffle.database.dao.UserDao
import com.revature.pixelraffle.viewmodel.UserViewModel


@Database(entities = [UserRow::class], version = 1, exportSchema = false)
abstract class AppDataBase: RoomDatabase() {
    abstract fun user_Dao(): UserDao

    companion object{
        @Volatile
        private var INSTANCE: AppDataBase ?=  null;

        fun getDatabase(context: Context):AppDataBase{
            val tempInstance = INSTANCE
            if(tempInstance != null){
                return tempInstance
            }
            synchronized(this){
                var instance = Room.databaseBuilder(context.applicationContext,
                    AppDataBase::class.java, "Adama C.")
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }

}

fun deleteDataBase(viewModel: UserViewModel){

}