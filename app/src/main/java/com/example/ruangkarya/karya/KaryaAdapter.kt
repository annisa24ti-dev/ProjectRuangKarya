package com.example.ruangkarya.karya

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ruangkarya.data.entity.KaryaEntity
import com.example.ruangkarya.databinding.ItemKaryaBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class KaryaAdapter(
    private val karyaList: List<KaryaEntity>,
    private val onDelete: (KaryaEntity) -> Unit
) : RecyclerView.Adapter<KaryaAdapter.KaryaViewHolder>() {

    class KaryaViewHolder(
        val binding: ItemKaryaBinding
    ) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): KaryaViewHolder {

        val binding = ItemKaryaBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return KaryaViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: KaryaViewHolder,
        position: Int
    ) {

        val karya = karyaList[position]
        val binding = holder.binding

        binding.tvNamaKarya.text = karya.namaKarya
        binding.tvKategori.text = karya.kategori
        binding.tvHarga.text = "Rp${karya.harga}"
        binding.tvDeskripsi.text = karya.deskripsi

        binding.btnHapusKarya.setOnClickListener {

            MaterialAlertDialogBuilder(holder.itemView.context)
                .setTitle("Hapus Karya")
                .setMessage(
                    "Apakah kamu yakin ingin menghapus ${karya.namaKarya}?"
                )
                .setPositiveButton("Ya") { _, _ ->
                    onDelete(karya)
                }
                .setNegativeButton("Tidak", null)
                .show()
        }
    }

    override fun getItemCount(): Int = karyaList.size
}
