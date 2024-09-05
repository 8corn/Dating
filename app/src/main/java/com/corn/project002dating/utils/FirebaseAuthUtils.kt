package com.corn.project002dating.utils

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.corn.project002dating.R
import com.corn.project002dating.databinding.ActivityFirebaseAuthUtilsBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class FirebaseAuthUtils : AppCompatActivity() {
    private lateinit var binding: ActivityFirebaseAuthUtilsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityFirebaseAuthUtilsBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    companion object {
        private lateinit var auth: FirebaseAuth
        fun getUid() : String {
            auth = Firebase.auth
            return auth.currentUser?.uid.toString()
        }
    }
}