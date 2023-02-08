package com.viren.panvalidation

import android.app.DatePickerDialog
import android.os.Build
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.viren.panvalidation.databinding.ActivityMainBinding
import java.text.SimpleDateFormat
import java.util.*


class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    val calender = Calendar.getInstance()
    lateinit var viewModel: MainActivityViewModel

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[MainActivityViewModel::class.java]


        binding.tilDOBDate.setOnClickListener {
            openDatePicker()
        }

        binding.tilDOBMonth.setOnClickListener {
            openDatePicker()
        }

        binding.tilDOBYear.setOnClickListener {
            openDatePicker()
        }

        binding.edtPan.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable?) {
                if (viewModel.validatePanNumber(s.toString())) {
                    binding.btnNext.isEnabled = true
                    binding.btnNext.setBackgroundColor(this@MainActivity.resources.getColor(R.color.purple_500))
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

    private fun openDatePicker() {
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
}