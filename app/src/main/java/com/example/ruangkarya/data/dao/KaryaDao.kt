package com.example.ruangkarya.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.ruangkarya.data.entity.KaryaEntity

@Dao
interface KaryaDao {

    @Query("SELECT * FROM karya ORDER BY createdAt DESC")
    suspend fun getAll(): List<KaryaEntity>

    @Insert
    suspend fun insert(karya: KaryaEntity)

    @Delete
    suspend fun delete(karya: KaryaEntity)
}
