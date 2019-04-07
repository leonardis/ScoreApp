package com.leonardis.scoreapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.Navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import androidx.navigation.fragment.NavHostFragment
import com.leonardis.scoreapp.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.view.navigationView

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        setupBottomNavMenu()
    }

    private fun setupBottomNavMenu() {
        binding.navigationView.let {
            val navController = findNavController(this, R.id.container)
            navController.setGraph(R.navigation.score_navigation)
            it.setupWithNavController(navController)
        }
    }
}
