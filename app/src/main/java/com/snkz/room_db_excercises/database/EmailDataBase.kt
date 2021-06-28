package com.snkz.room_db_excercises.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.snkz.room_db_excercises.dao.EmailDao
import com.snkz.room_db_excercises.entity.Email
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.internal.synchronized

@Database(
    entities = [Email :: class],
    version = 1,
    exportSchema = false
)

abstract class EmailDataBase : RoomDatabase() {
    abstract fun emailDao() : EmailDao

    companion object{
        @Volatile
        private var INSTANCE : EmailDataBase? = null
        @InternalCoroutinesApi
        fun getInstance(context: Context): EmailDataBase {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    EmailDataBase ::class.java, "email_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                    .also { INSTANCE = it }
            }
        }

    }
}