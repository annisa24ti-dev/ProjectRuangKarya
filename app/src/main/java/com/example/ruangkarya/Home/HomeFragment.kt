package com.example.ruangkarya.home

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.ruangkarya.DetailActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ruangkarya.databinding.FragmentHomeBinding
import com.example.ruangkarya.home.tutorial.NinthActivity

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private lateinit var productAdapter: ProductAdapter

    private val productList = listOf(
        ProductModel("Vas Keramik", "Kriya", "Rp89.000", "🏺"),
        ProductModel("Tas Anyaman", "Fashion", "Rp125.000", "👜"),
        ProductModel("Batik Lokal", "Fashion", "Rp150.000", "👘"),
        ProductModel("Gelang Handmade", "Kriya", "Rp35.000", "📿"),
        ProductModel("Keripik Pisang", "Kuliner", "Rp20.000", "🍌"),
        ProductModel("Kue Tradisional", "Kuliner", "Rp30.000", "🍪"),
        ProductModel("Tempat Pensil Rotan", "Kriya", "Rp45.000", "🧺"),
        ProductModel("Dompet Kain", "Fashion", "Rp65.000", "👛")
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentHomeBinding.inflate(
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

        setupGreeting()
        setupRecyclerView()
        setupTabLayout()

        binding.btnExploreComponents.setOnClickListener {
            startActivity(Intent(requireContext(), NinthActivity::class.java))
        }
    }

    private fun setupGreeting() {
        val sharedPref = requireContext().getSharedPreferences(
            "user_pref",
            Context.MODE_PRIVATE
        )

        val username = sharedPref.getString("username", "Pengguna")

        binding.tvGreeting.text = "Selamat datang, $username!"
    }

    private fun setupRecyclerView() {

        productAdapter = ProductAdapter(productList) { product ->
            val intent = Intent(requireContext(), DetailActivity::class.java).apply {
                putExtra("judul", product.name)
                putExtra(
                    "deskripsi",
                    "Kategori: ${product.category}\nHarga: ${product.price}\n\nProduk ini merupakan salah satu karya lokal yang ditampilkan di RuangKarya."
                )
            }
            startActivity(intent)
        }

        binding.recyclerProducts.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = productAdapter
            setHasFixedSize(true)
        }
    }

    private fun setupTabLayout() {

        val categories = listOf(
            "Semua",
            "Kriya",
            "Fashion",
            "Kuliner"
        )

        categories.forEach { category ->
            binding.tabLayoutCategory.addTab(
                binding.tabLayoutCategory.newTab().setText(category)
            )
        }

        binding.tabLayoutCategory.addOnTabSelectedListener(
            object : com.google.android.material.tabs.TabLayout.OnTabSelectedListener {

                override fun onTabSelected(
                    tab: com.google.android.material.tabs.TabLayout.Tab?
                ) {
                    val selectedCategory = tab?.text.toString()

                    val filteredProducts = if (selectedCategory == "Semua") {
                        productList
                    } else {
                        productList.filter {
                            it.category == selectedCategory
                        }
                    }

                    productAdapter.updateData(filteredProducts)
                }

                override fun onTabUnselected(
                    tab: com.google.android.material.tabs.TabLayout.Tab?
                ) {}

                override fun onTabReselected(
                    tab: com.google.android.material.tabs.TabLayout.Tab?
                ) {}
            }
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
