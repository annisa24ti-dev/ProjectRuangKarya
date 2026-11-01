package com.example.ruangkarya

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.ruangkarya.databinding.DetailactivityBinding

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: DetailactivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DetailactivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val judul = intent.getStringExtra("judul")
        val deskripsi = intent.getStringExtra("deskripsi")

        if (judul != null) {
            binding.txtJudul.text = judul
        }

        if (deskripsi != null) {
            binding.txtDeskripsi.text = deskripsi
        }

        binding.btnKembali.setOnClickListener {
            finish()
        }
    }
}
