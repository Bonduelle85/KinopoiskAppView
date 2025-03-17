package com.example.kinopoiskappview.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import kotlinx.coroutines.CoroutineScope

@Database(entities = [MovieDbModel::class,], version = 1, exportSchema = false,)
@TypeConverters(TypeConverter::class)

abstract class AppDatabase : RoomDatabase() {

    abstract fun moviesDao(): MoviesDao

    companion object {

        @Volatile
        private var INSTANCE: AppDatabase? = null

        private val MONITOR = Any()

        fun getInstance(context: Context, scope: CoroutineScope): AppDatabase {
            INSTANCE?.let {
                return it
            }

            synchronized(MONITOR) {
                INSTANCE?.let {
                    return it
                }

                val db = Room.databaseBuilder(
                    context,
                    AppDatabase::class.java,
                    AppDatabase::class.java.simpleName,
                ).build()

                INSTANCE = db

                return db
            }
        }
    }
}