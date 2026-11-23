
package com.example.ruangkarya

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.example.ruangkarya.databinding.ActivityBaseBinding
import com.example.ruangkarya.home.HomeFragment
import com.example.ruangkarya.message.MessageFragment
import com.example.ruangkarya.profile.MoreFragment
import com.example.ruangkarya.karya.KaryaFragment

class BaseActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBaseBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityBaseBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.mainToolbar)
        supportActionBar?.title = "RuangKarya"

        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { view, insets ->

            val systemBars = insets.getInsets(
                WindowInsetsCompat.Type.systemBars()
            )

            view.setPadding(
                systemBars.left,
                systemBars.top,
                systemBars.right,
                0
            )

            insets
        }

        if (savedInstanceState == null) {
            replaceFragment(HomeFragment())
            binding.bottomNavView.selectedItemId = R.id.home
        }

        binding.bottomNavView.setOnItemSelectedListener { item ->

            when (item.itemId) {

                R.id.home -> {
                    replaceFragment(HomeFragment())
                    true
                }

                R.id.message -> {
                    replaceFragment(MessageFragment())
                    true
                }

                R.id.karya -> {
                    replaceFragment(KaryaFragment())
                    true
                }

                R.id.more -> {
                    replaceFragment(MoreFragment())
                    true
                }

                else -> false
            }
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(binding.fragmentContainer.id, fragment)
            .commit()
    }
}
