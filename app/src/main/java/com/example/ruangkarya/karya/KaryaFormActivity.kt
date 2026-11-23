package com.example.ruangkarya.karya

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.ruangkarya.data.AppDatabase
import com.example.ruangkarya.data.entity.KaryaEntity
import com.example.ruangkarya.databinding.ActivityKaryaFormBinding
import kotlinx.coroutines.launch

class KaryaFormActivity : AppCompatActivity() {

    private lateinit var binding: ActivityKaryaFormBinding
    private lateinit var db: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityKaryaFormBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = AppDatabase.getInstance(this)

        binding.btnSimpanKarya.setOnClickListener {

            val nama = binding.etNamaKarya.text.toString().trim()
            val kategori = binding.etKategori.text.toString().trim()
            val harga = binding.etHarga.text.toString().trim()
            val deskripsi = binding.etDeskripsi.text.toString().trim()

            if (nama.isBlank() || kategori.isBlank() ||
                harga.isBlank() || deskripsi.isBlank()) {

                Toast.makeText(
                    this,
                    "Lengkapi semua kolom!",
                    Toast.LENGTH_SHORT
                ).show()

                return@setOnClickListener
            }

            binding.btnSimpanKarya.isEnabled = false

            lifecycleScope.launch {
                try {
                    db.karyaDao().insert(
                        KaryaEntity(
                            namaKarya = nama,
                            kategori = kategori,
                            harga = harga,
                            deskripsi = deskripsi
                        )
                    )

                    Toast.makeText(
                        this@KaryaFormActivity,
                        "Karya berhasil disimpan",
                        Toast.LENGTH_SHORT
                    ).show()

                    finish()

                } catch (e: Exception) {
                    binding.btnSimpanKarya.isEnabled = true

                    Toast.makeText(
                        this@KaryaFormActivity,
                        "Gagal menyimpan karya",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }
}
