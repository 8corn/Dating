package com.corn.project002dating.auth

import android.app.DatePickerDialog
import android.content.Intent
import android.graphics.Color
import android.icu.util.Calendar
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.corn.project002dating.R
import com.corn.project002dating.databinding.ActivityJoinBinding
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.util.Locale

class JoinActivity : AppCompatActivity() {
    private lateinit var binding: ActivityJoinBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var dateTextView: TextView
    private lateinit var selectDateButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityJoinBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = Firebase.auth

        dateTextView = binding.birthtxt

        val now = LocalDate.now()
        Log.d("시스템 시간 : ", "$now")

        binding.birthbtn.setOnClickListener {
            binding.birthtxt.visibility = View.VISIBLE
            showDatePickerDialog()
        }

        val genderadapter = ArrayAdapter.createFromResource(this, R.array.gender, android.R.layout.simple_spinner_item)
        genderadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.gender.adapter = genderadapter

        val mbtiadapter = ArrayAdapter.createFromResource(this, R.array.mbti, android.R.layout.simple_spinner_item)
        mbtiadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.mbti.adapter = mbtiadapter

        val bloodadapter = ArrayAdapter.createFromResource(this, R.array.blood, android.R.layout.simple_spinner_item)
        bloodadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.blood.adapter = bloodadapter

        val constellationadapter = ArrayAdapter.createFromResource(this, R.array.constellation, android.R.layout.simple_spinner_item)
        constellationadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.constellation.adapter = constellationadapter

        val spinners = listOf(binding.gender, binding.mbti, binding.blood, binding.constellation)
        spinners.forEach { spinner ->
            spinner.post {
                (spinner.selectedView as? TextView)?.setTextColor(Color.parseColor("#A2A2A2"))
            }
        }

        binding.gender.setSelection(0)
        binding.mbti.setSelection(0)
        binding.blood.setSelection(0)
        binding.constellation.setSelection(0)

        binding.gender.onItemSelectedListener = object: AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                when (position) {
                    0 -> {
                        binding.body.visibility = View.GONE
                        binding.talltype.visibility = View.GONE
                        binding.wamenhair.visibility = View.GONE
                        binding.manhair.visibility = View.GONE
                    }
                    1 -> {
                        binding.body.visibility = View.VISIBLE
                        binding.talltype.visibility = View.VISIBLE
                        binding.wamenhair.visibility = View.GONE
                        binding.manhair.visibility = View.VISIBLE
                    }
                    2 -> {
                        binding.body.visibility = View.VISIBLE
                        binding.talltype.visibility = View.VISIBLE
                        binding.wamenhair.visibility = View.VISIBLE
                        binding.manhair.visibility = View.GONE
                    }

                }
            }
            override fun onNothingSelected(parent: AdapterView<*>) {
            }
        }
        binding.join.setOnClickListener {
            auth.createUserWithEmailAndPassword(
                binding.idaddress.text.toString(),
                binding.password.text.toString()
            ).addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    Log.d("JoinActivity", "회원가입 실패")
                    val intent = Intent(this, LoginActivity::class.java)
                    startActivity(intent)
                } else {
                    val intent = Intent(this, LikeTypeActivity::class.java)
                    startActivity(intent)
                }
            }
        }
    }

    private fun showDatePickerDialog() {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DATE)

        val datePickerDialog = DatePickerDialog(this, { _, Year, Month, Day ->
            val selectedDate = Calendar.getInstance().apply {
                set(Year, Month, Day)
            }

            val dateFormat = SimpleDateFormat("yyyyMMdd", Locale.getDefault())
            val dateString = dateFormat.format(selectedDate.time)
            dateTextView.text = dateString
        }, year, month, day)
        datePickerDialog.show()
    }
}