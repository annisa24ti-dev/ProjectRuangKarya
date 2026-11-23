package com.example.ruangkarya.profile

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.example.ruangkarya.LoginActivity
import com.example.ruangkarya.databinding.FragmentMoreBinding

class MoreFragment : Fragment() {

    private var _binding: FragmentMoreBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMoreBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val sharedPref = requireContext().getSharedPreferences(
            "user_pref",
            android.content.Context.MODE_PRIVATE
        )

        val username = sharedPref.getString("username", "Pengguna")
        val email = sharedPref.getString("email", "Email belum tersedia")

        binding.tvProfileName.text = username
        binding.tvProfileEmail.text = email

        binding.btnLogout.setOnClickListener {
            showLogoutDialog()
        }
    }

    private fun showLogoutDialog() {
        AlertDialog.Builder(requireContext())
            .setTitle("Keluar Akun")
            .setMessage("Apakah kamu yakin ingin keluar dari akun?")
            .setPositiveButton("Ya") { _, _ ->

                requireContext().getSharedPreferences(
                    "user_pref",
                    android.content.Context.MODE_PRIVATE
                ).edit().clear().apply()

                val intent = Intent(requireContext(), LoginActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or
                        Intent.FLAG_ACTIVITY_CLEAR_TASK

                startActivity(intent)
            }
            .setNegativeButton("Tidak", null)
            .show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
