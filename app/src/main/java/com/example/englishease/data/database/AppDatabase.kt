package com.example.englishease.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.englishease.data.models.Question
import com.example.englishease.data.models.Test
import com.example.englishease.data.models.Result
import com.example.englishease.data.models.Conclusion

@Database(entities = [
                Test::class,
                Question::class,
                Result::class,
                Conclusion::class
                     ],
            version = 2,
            exportSchema = false)

abstract class AppDatabase: RoomDatabase() {

    companion object {
        private var db: AppDatabase? = null
        private const val DB_NAME = "englishease.db"
        private val LOCK = Any()

        fun getInstance(context: Context): AppDatabase {
            synchronized(LOCK) {
                db?.let { return it }
                val instance = Room.databaseBuilder(
                    context,
                    AppDatabase::class.java,
                    DB_NAME
                ).fallbackToDestructiveMigration()
                    .build()
                db = instance
                return instance
            }
        }
    }

    abstract fun appDao(): AppDao
}