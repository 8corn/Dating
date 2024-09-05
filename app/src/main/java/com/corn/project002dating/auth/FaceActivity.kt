package com.corn.project002dating.auth

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.corn.project002dating.MainActivity
import com.corn.project002dating.R
import com.corn.project002dating.databinding.ActivityFaceBinding

class FaceActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFaceBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityFaceBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Handler().postDelayed({
            startActivity(Intent(this, LoadingActivity::class.java))
            finish()
        },5000)
    }
}