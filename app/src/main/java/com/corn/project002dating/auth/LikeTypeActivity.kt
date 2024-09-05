package com.corn.project002dating.auth

import android.content.Intent
import android.os.Bundle
import android.graphics.Color
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import androidx.appcompat.app.AppCompatActivity
import com.corn.project002dating.databinding.ActivityLikeTypeBinding

class LikeTypeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLikeTypeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLikeTypeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val text = "카테고리 당 최소 1개 이상 선택해 주세요."

        val spannableString = SpannableString(text)

        val startIndex = text.indexOf("1개 이상")
        val endIndex = startIndex + "1개 이상".length

        spannableString.setSpan(
            ForegroundColorSpan(Color.parseColor("#FA0D54")),
            startIndex,
            endIndex,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        binding.txtPlease.text = spannableString

        binding.likenext.setOnClickListener {
            val intent = Intent(this, FaceActivity::class.java)
            startActivity(intent)
        }
    }
}