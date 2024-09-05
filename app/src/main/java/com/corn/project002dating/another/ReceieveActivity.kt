package com.corn.project002dating.another

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.corn.project002dating.databinding.ActivityReceieveBinding

class ReceieveActivity : AppCompatActivity() {
    private lateinit var binding: ActivityReceieveBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityReceieveBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}