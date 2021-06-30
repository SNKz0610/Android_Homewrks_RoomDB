package com.snkz.room_db_excercises.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.Room.databaseBuilder
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.snkz.room_db_excercises.dao.EmailDao
import com.snkz.room_db_excercises.entity.Email
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.internal.synchronized
import kotlinx.coroutines.launch

@Database(
    entities = [Email :: class],
    version = 1,
    exportSchema = false
)

abstract class EmailDataBase : RoomDatabase() {

    abstract fun emailDao() : EmailDao

    @InternalCoroutinesApi
    companion object{
        @Volatile
        private var INSTANCE: EmailDataBase? = null

        fun getInstance(context: Context): EmailDataBase =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
            }

        private fun buildDatabase(context: Context) =
            databaseBuilder(context.applicationContext,
                EmailDataBase::class.java, "email_database")
                // prepopulate the database after onCreate was called
                .addCallback(object : Callback() {
                    override fun onCreate(db: SupportSQLiteDatabase) {
                        super.onCreate(db)
                        // insert the data on the IO Thread
//                        CoroutineScope(Dispatchers.IO).launch {
//                            getInstance(context).emailDao().addEmail(PREPOPULATE_DATA)
//                        }
                        ioThread {
                            getInstance(context).emailDao().addEmail(PREPOPULATE_DATA)
                        }
                    }
                })
                .fallbackToDestructiveMigration()
                .build()


        val PREPOPULATE_DATA = arrayListOf(
            Email( "cutor@gmail.com","cyka blyat", "finished him?"),
            Email( "lukaser@gmail.com","gunship", "gimme that gun"),
            Email( "wejok@gmail.com","warning", "he got away already"),
            Email( "lavendar@gmail.com","dangerous", "Bomb has been planted"),
            Email( "junweak@gmail.com","1 tap", "just only 1 bullet")
        )
    }
}