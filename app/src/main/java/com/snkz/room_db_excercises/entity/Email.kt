package com.snkz.room_db_excercises.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "email")
data class Email(
    val senderEmail : String,
    val title : String,
    val content : String
    ) {
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
}
