package com.example.ruangkarya.Home.pertemuan9

import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.ruangkarya.databinding.ActivityNinthBinding
import com.google.android.material.chip.Chip

class NinthActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNinthBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNinthBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Setup Toolbar
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // Form Login / Simpan Button
        binding.btnLogin.setOnClickListener {
            val name = binding.etName.text.toString().trim()
            if (name.isNotEmpty()) {
                Toast.makeText(this, "Data tersimpan: $name", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Nama belum diisi", Toast.LENGTH_SHORT).show()
            }
        }

        // A. Listener ChipGroup
        binding.chipGroupFilter.setOnCheckedStateChangeListener { group, checkedIds ->
            if (checkedIds.isNotEmpty()) {
                val selectedChip = group.findViewById<Chip>(checkedIds.first())
                Toast.makeText(
                    this,
                    "Kategori dipilih: ${selectedChip.text}",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        // C. Listener Tombol GridLayout
        val buttons = listOf(
            binding.btnMenu1, binding.btnMenu2, binding.btnMenu3,
            binding.btnMenu4, binding.btnMenu5, binding.btnMenu6
        )

        buttons.forEach { button ->
            button.setOnClickListener {
                Toast.makeText(
                    this,
                    "Menu '${(it as Button).text}' diklik",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}