package com.viren.panvalidation

import android.R
import android.app.DatePickerDialog
import android.os.Build
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.viren.panvalidation.databinding.ActivityMainBinding
import java.text.SimpleDateFormat
import java.util.*
import java.util.regex.Matcher
import java.util.regex.Pattern


class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    val calender = Calendar.getInstance()

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)


        binding.tilDOBDate.setOnClickListener {
            val mcurrentDate = Calendar.getInstance()
            val mYear = mcurrentDate[Calendar.YEAR]
            val mMonth = mcurrentDate[Calendar.MONTH]
            val mDay = mcurrentDate[Calendar.DAY_OF_MONTH]

            val mDatePicker = DatePickerDialog(
                this@MainActivity,
                { datepicker, selectedyear, selectedmonth, selectedday ->
                    mcurrentDate[Calendar.YEAR] = selectedyear
                    mcurrentDate[Calendar.MONTH] = selectedmonth
                    mcurrentDate[Calendar.DAY_OF_MONTH] = selectedday
                    val sdf = SimpleDateFormat("yyyy-mm-dd")
                    binding.tilDOBDate.setText(selectedday.toString())
                    binding.tilDOBMonth.setText(selectedmonth.toString())
                    binding.tilDOBYear.setText(selectedyear.toString())
                }, mYear, mMonth, mDay
            )

            mDatePicker.datePicker.maxDate = calender.timeInMillis

            mDatePicker.show()
        }

        binding.edtPan.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable?) {
                if (panValidation(s)) {
                    binding.btnNext.isEnabled = true
                    binding.btnNext.setBackgroundColor(this@MainActivity.resources.getColor(R.color.holo_blue_dark))
                }
            }

        })

        binding.btnNext.setOnClickListener {
            Toast.makeText(this, "Details submitted successfully", Toast.LENGTH_SHORT).show()
            finish()
        }


        binding.txtDoNotHavePan.setOnClickListener {
            finish()
        }

    }

    private fun panValidation(panValue: Editable?): Boolean {

        val pattern: Pattern = Pattern.compile("[A-Z]{5}[0-9]{4}[A-Z]{1}")
        val matcher: Matcher = pattern.matcher(panValue)
        return matcher.matches()

    }
}