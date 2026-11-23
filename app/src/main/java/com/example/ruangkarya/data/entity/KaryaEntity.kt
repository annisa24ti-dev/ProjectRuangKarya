package com.example.ruangkarya.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "karya")
data class KaryaEntity(

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    val namaKarya: String,
    val kategori: String,
    val harga: String,
    val deskripsi: String,

    val createdAt: Long = System.currentTimeMillis()
)
