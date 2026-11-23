package com.example.ruangkarya.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.ruangkarya.data.dao.KaryaDao
import com.example.ruangkarya.data.entity.KaryaEntity

@Database(
    entities = [KaryaEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun karyaDao(): KaryaDao

    companion object {

        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {

            return INSTANCE ?: synchronized(this) {

                INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "ruangkarya_database"
                ).build().also {
                    INSTANCE = it
                }
            }
        }
    }
}
