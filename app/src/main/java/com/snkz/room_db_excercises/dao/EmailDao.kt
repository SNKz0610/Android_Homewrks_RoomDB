package com.snkz.room_db_excercises.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.snkz.room_db_excercises.entity.Email

@Dao
interface EmailDao {
    @Insert
    fun addEmail(email : Email)

    @Insert
    fun addEmail(email : ArrayList<Email>)

    @Query("SELECT * FROM email")
    fun getAllEmail() : LiveData<List<Email>>
}