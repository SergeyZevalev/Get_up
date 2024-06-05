package com.example.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.presentation.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportFragmentManager.beginTransaction().replace(R.id.mainRootLayout,
            MainFragment()).commit()
    }
}