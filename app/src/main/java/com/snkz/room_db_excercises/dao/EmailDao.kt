package com.snkz.room_db_excercises.dao

import androidx.room.Dao
import androidx.room.Insert
import com.snkz.room_db_excercises.entity.Email

@Dao
interface EmailDao {
    @Insert
    fun addEmail(email : Email)
}