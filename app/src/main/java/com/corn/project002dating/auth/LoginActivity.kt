package com.corn.project002dating.auth

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.corn.project002dating.MainActivity
import com.corn.project002dating.R
import com.corn.project002dating.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = Firebase.auth
        binding.login.setOnClickListener {
            auth.signInWithEmailAndPassword(binding.email.text.toString(), binding.password.text.toString()).addOnCompleteListener(this) { task ->
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
//                    startActivity(intent)
//                if (task.isSuccessful) {
//                    val intent = Intent(this, MainActivity::class.java)
//                    startActivity(intent)
//                } else {
////                    Toast.makeText(this, "로그인 정보를 확인해주세요", Toast.LENGTH_SHORT).show()
//                }
            }
        }

        binding.join.setOnClickListener {
            val intent = Intent(this, JoinActivity::class.java)
            startActivity(intent)
        }

        binding.forgotid.setOnClickListener {
            val intent = Intent(this, ForgotActivity::class.java)
            startActivity(intent)
        }
    }
}