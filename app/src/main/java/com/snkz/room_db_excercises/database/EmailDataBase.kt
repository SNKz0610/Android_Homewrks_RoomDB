package com.snkz.room_db_excercises.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.Room.databaseBuilder
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
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

    @Volatile private var INSTANCE: EmailDataBase? = null

    @OptIn(InternalCoroutinesApi::class)
    fun getInstance(context: Context): EmailDataBase =
        INSTANCE ?: synchronized(this) {
            INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
        }

    private fun buildDatabase(context: Context) =
        databaseBuilder(context.applicationContext,
            EmailDataBase::class.java, "Sample.db")
            // prepopulate the database after onCreate was called
            .addCallback(object : Callback() {
                override fun onCreate(db: SupportSQLiteDatabase) {
                    super.onCreate(db)
                    // insert the data on the IO Thread
                    ioThread {
                        getInstance(context).emailDao().addEmail(PREPOPULATE_DATA)
                    }
                }
            })
            .build()

    val PREPOPULATE_DATA = listOf(
        Email(0, "cutor@gmail.com","cyka blyat", "finished him?"),
        Email(1, "lukaser@gmail.com","gunship", "gimme that gun"),
        Email(2, "wejok@gmail.com","warning", "he got away already"),
        Email(3, "lavendar@gmail.com","dangerous", "Bomb has been planted"),
        Email(4, "junweak@gmail.com","1 tap", "just only 1 bullet")
    )
}