package com.example.lab3_androidadvanced

import android.Manifest
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.lab3_androidadvanced.databinding.ActivityBai2Binding

class Bai2 : AppCompatActivity() {
    lateinit var binding: ActivityBai2Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityBai2Binding.inflate(layoutInflater)
        setContentView(binding.root)
        super.onCreate(savedInstanceState)


    }


}