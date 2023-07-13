package com.gosty.suitmediamobiledevtest.ui.first

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.gosty.suitmediamobiledevtest.R
import com.gosty.suitmediamobiledevtest.databinding.ActivityFirstBinding

class FirstActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFirstBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityFirstBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}