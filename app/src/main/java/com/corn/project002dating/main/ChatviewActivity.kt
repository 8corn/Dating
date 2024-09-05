package com.corn.project002dating.main

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.corn.project002dating.R
import com.corn.project002dating.databinding.ActivityChatviewBinding

class ChatviewActivity : AppCompatActivity() {
    private lateinit var binding: ActivityChatviewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityChatviewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val chatFragment = ChatFragment()
        val bundle = Bundle().apply {
            putString("nickname", "YourNickname")
        }
        chatFragment.arguments = bundle

        supportFragmentManager.beginTransaction()
            .replace(R.id.layout_frame, chatFragment)
            .commit()
    }
}