package com.snkz.room_db_excercises.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Email(
    @PrimaryKey(autoGenerate = true) val id : Long = 0,
    val senderEmail : String,
    val title : String,
    val content : String
    )
