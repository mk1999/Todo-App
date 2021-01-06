package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myapplication.databinding.EditpageBinding
import com.google.android.material.datepicker.MaterialDatePicker
import kotlinx.android.synthetic.main.create_todoform.*
import kotlinx.android.synthetic.main.editpage.*

class EditpageActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.editpage)

        val binding = EditpageBinding.inflate(layoutInflater)
        binding.activityEditET.setText(intent.getStringExtra("ACTIVITYNAME"))
        binding.dateEditET.setText(intent.getStringExtra("DEADLINE"))
        binding.statusSpinEdit.setText(intent.getStringExtra("STATUS"))

        binding.btnCreateEdit.setOnClickListener {
            var nameSet = binding.activityEditET.text.toString()
            var dateSet = binding.dateEditET.text.toString()
            var statusSet = binding.statusSpinEdit.text.toString()

            val intent = Intent(this,HomeScreenActivity::class.java)
            intent.putExtra("Name",nameSet)
            intent.putExtra("Date",dateSet)
            intent.putExtra("Status",statusSet)
            startActivity(intent)
        }

        setContentView(binding.root)

        displayDate()

    }

    private fun displayDate(){
        val builder: MaterialDatePicker.Builder<*> = MaterialDatePicker.Builder.datePicker()
        builder.setTitleText("Select a Date")
        val picker: MaterialDatePicker<*> = builder.build()
        dateEditET.setOnClickListener {
            picker.show(supportFragmentManager,picker.toString())
        }
        picker.addOnPositiveButtonClickListener {
            dateEditET.setText(picker.headerText)
        }
    }

}