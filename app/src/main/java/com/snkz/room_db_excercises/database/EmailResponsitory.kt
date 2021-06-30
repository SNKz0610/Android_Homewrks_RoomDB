package com.snkz.room_db_excercises.database

import android.app.Application
import kotlinx.coroutines.InternalCoroutinesApi

class EmailResponsitory (application: Application) {
    @InternalCoroutinesApi
    val dataBase = EmailDataBase.getInstance(application)

    @InternalCoroutinesApi
    fun getAllEmail() = dataBase.emailDao().getAllEmail()
}