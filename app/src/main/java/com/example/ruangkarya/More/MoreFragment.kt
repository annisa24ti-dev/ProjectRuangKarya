
package com.example.ruangkarya.More

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SimpleAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.ruangkarya.databinding.FragmentMoreBinding

class MoreFragment : Fragment() {

    private var _binding: FragmentMoreBinding? = null
    private val binding get() = _binding!!

    private val dataListWithDesc = listOf(
        mapOf(
            "title" to "Kotlin",
            "desc" to "Bahasa untuk Android modern"
        ),
        mapOf(
            "title" to "Java",
            "desc" to "Bahasa pemrograman berorientasi objek"
        ),
        mapOf(
            "title" to "Python",
            "desc" to "Bahasa yang mudah dipelajari"
        ),
        mapOf(
            "title" to "C++",
            "desc" to "Bahasa untuk aplikasi berkinerja tinggi"
        ),
        mapOf(
            "title" to "JavaScript",
            "desc" to "Bahasa untuk web interaktif"
        ),
        mapOf(
            "title" to "Dart",
            "desc" to "Bahasa yang digunakan Flutter"
        ),
        mapOf(
            "title" to "Swift",
            "desc" to "Bahasa untuk aplikasi Apple"
        ),
        mapOf(
            "title" to "Go",
            "desc" to "Bahasa untuk layanan dan sistem"
        ),
        mapOf(
            "title" to "Ruby",
            "desc" to "Bahasa yang dikenal lewat Ruby on Rails"
        ),
        mapOf(
            "title" to "R",
            "desc" to "Bahasa untuk analisis statistik"
        )
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMoreBinding.inflate(
            inflater,
            container,
            false
        )
        return binding.root
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)

        // Mengatur Toolbar
        (requireActivity() as AppCompatActivity)
            .setSupportActionBar(binding.toolbar)

        (requireActivity() as AppCompatActivity)
            .supportActionBar?.title = "More"

        // Menampilkan daftar bahasa pemrograman
        val adapter = SimpleAdapter(
            requireContext(),
            dataListWithDesc,
            android.R.layout.simple_list_item_2,
            arrayOf("title", "desc"),
            intArrayOf(
                android.R.id.text1,
                android.R.id.text2
            )
        )

        binding.listViewItems.adapter = adapter

        // Menampilkan Toast saat item dipilih
        binding.listViewItems.setOnItemClickListener {
                _, _, position, _ ->

            val selectedItem = dataListWithDesc[position]
            val title = selectedItem["title"]
            val desc = selectedItem["desc"]

            Toast.makeText(
                requireContext(),
                "Kamu memilih: $title ($desc)",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
