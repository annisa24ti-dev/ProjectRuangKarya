package com.example.ruangkarya.karya

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ruangkarya.data.AppDatabase
import com.example.ruangkarya.data.entity.KaryaEntity
import com.example.ruangkarya.databinding.FragmentKaryaBinding
import kotlinx.coroutines.launch

class KaryaFragment : Fragment() {

    private var _binding: FragmentKaryaBinding? = null
    private val binding get() = _binding!!

    private lateinit var db: AppDatabase
    private lateinit var adapter: KaryaAdapter

    private val karyaList = mutableListOf<KaryaEntity>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentKaryaBinding.inflate(
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

        db = AppDatabase.getInstance(requireContext())

        adapter = KaryaAdapter(karyaList) { karya ->
            hapusKarya(karya)
        }

        binding.rvKarya.layoutManager =
            LinearLayoutManager(requireContext())

        binding.rvKarya.adapter = adapter

        binding.fabTambahKarya.setOnClickListener {
            startActivity(
                Intent(
                    requireContext(),
                    KaryaFormActivity::class.java
                )
            )
        }
    }

    override fun onResume() {
        super.onResume()

        if (_binding != null) {
            muatKarya()
        }
    }

    private fun muatKarya() {
        viewLifecycleOwner.lifecycleScope.launch {

            val data = db.karyaDao().getAll()

            karyaList.clear()
            karyaList.addAll(data)

            adapter.notifyDataSetChanged()

            binding.tvKaryaInfo.text =
                "Total karya tersimpan: ${data.size}"
        }
    }

    private fun hapusKarya(karya: KaryaEntity) {
        viewLifecycleOwner.lifecycleScope.launch {

            db.karyaDao().delete(karya)

            muatKarya()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
