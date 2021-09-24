package com.example.lab3_androidadvanced

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.lab3_androidadvanced.databinding.ActivityMainBinding

class PH15766MainActivity : AppCompatActivity() {
    // Họ tên: Nông Ngọc Diệu
    // MSSV: DieuNNPH15766
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnBai1.setOnClickListener {
            startActivity(Intent(this, Bai1::class.java))
        }


    }
}