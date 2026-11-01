
package com.example.ruangkarya.Home

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.ruangkarya.DetailActivity
import com.example.ruangkarya.Home.pertemuan9.NinthActivity
import com.example.ruangkarya.LoginActivity
import com.example.ruangkarya.databinding.FragmentHomeBinding
import com.example.ruangkarya.pertemuan_5.LimaActivity
import com.example.ruangkarya.pertemuan_5.WebViewActivity
import com.google.android.material.snackbar.Snackbar

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (requireActivity() as AppCompatActivity)
            .setSupportActionBar(binding.toolbar)

        (requireActivity() as AppCompatActivity)
            .supportActionBar?.title = "RuangKarya"

        val sharedPref = requireContext().getSharedPreferences(
            "user_pref",
            Context.MODE_PRIVATE
        )

        val username = sharedPref.getString("username", "Pengguna")
        binding.tvWelcome.text = "Halo, $username"


        binding.btnDetail.setOnClickListener {
            val intent = Intent(
                requireContext(),
                DetailActivity::class.java
            )

            intent.putExtra("judul", "Rosela Fashion")
            intent.putExtra(
                "deskripsi",
                "Ruang Karya menyediakan beragam jenis pakaian " +
                        "yang fashionable dan up to date."
            )

            startActivity(intent)
        }

        // Tombol Notifikasi menampilkan Snackbar
        binding.btnSnackBar.setOnClickListener {
            Snackbar.make(
                binding.root,
                "Selamat datang di RuangKarya!",
                Snackbar.LENGTH_SHORT
            ).show()
        }

        // Tombol WebView membuka halaman WebView
        binding.btnWebView.setOnClickListener {
            startActivity(
                Intent(requireContext(), WebViewActivity::class.java)
            )
        }

        // Tombol Pertemuan 5 membuka LimaActivity
        binding.btnPertemuan5.setOnClickListener {
            startActivity(
                Intent(requireContext(), LimaActivity::class.java)
            )
        }

        // Tombol Pertemuan 9
        binding.btnPertemuan9.setOnClickListener {
            startActivity(Intent(requireContext(), NinthActivity::class.java))
        }

        // Tombol Logout menampilkan konfirmasi
        binding.btnLogout.setOnClickListener {
            AlertDialog.Builder(requireContext())
                .setTitle("Logout")
                .setMessage("Apakah Anda yakin ingin keluar?")
                .setPositiveButton("Ya") { dialog, _ ->
                    sharedPref.edit().clear().apply()
                    dialog.dismiss()

                    val intent = Intent(
                        requireContext(),
                        LoginActivity::class.java
                    )

                    intent.flags =
                        Intent.FLAG_ACTIVITY_NEW_TASK or
                                Intent.FLAG_ACTIVITY_CLEAR_TASK

                    startActivity(intent)
                }
                .setNegativeButton("Batal") { dialog, _ ->
                    dialog.dismiss()
                }
                .show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
