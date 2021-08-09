package com.fostiums.seaapp

import android.content.Intent
import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.fostiums.seaapp.databinding.ActivityMainBinding
import com.fostiums.seaapp.helper.TinyDB
import com.fostiums.seaapp.ui.auth.Login

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    lateinit var tinyDB : TinyDB
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        tinyDB = TinyDB(this)
        if ( tinyDB.getString("TOKEN") == null || tinyDB.getString("TOKEN") == ""){

            val toLogin = Intent(this,Login::class.java)
            startActivity(toLogin)
        }

            binding = ActivityMainBinding.inflate(layoutInflater)
            setContentView(binding.root)

            val navView: BottomNavigationView = binding.navView

            val navController = findNavController(R.id.nav_host_fragment_activity_main)

            navView.setupWithNavController(navController)

    }

    override fun onResume() {
        super.onResume()
        if ( tinyDB.getString("TOKEN") == null || tinyDB.getString("TOKEN") == ""){

            val toLogin = Intent(this,Login::class.java)
            startActivity(toLogin)
        }
    }
}