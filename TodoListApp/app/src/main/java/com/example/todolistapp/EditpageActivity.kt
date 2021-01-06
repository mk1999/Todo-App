package com.example.todolistapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.text.InputType.TYPE_NULL
import com.example.todolistapp.R
import com.example.todolistapp.databinding.EditpageBinding
import com.example.todolistapp.ui.home.HomeFragment
import com.google.android.material.datepicker.MaterialDatePicker
import kotlinx.android.synthetic.main.editpage.*
import java.text.DateFormat
import java.util.*

class EditpageActivity : AppCompatActivity() {
    private val requestCode = 1;
    val binding = EditpageBinding.inflate(layoutInflater)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.editpage)
        setContentView(binding.root)

        editPageDetails()
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

    private fun editPageDetails(){

        binding.activityEditET.setText(intent.getStringExtra("ACTIVITYNAME"))
        binding.dateEditET.setText(intent.getStringExtra("DEADLINE"))
        var status = intent.getStringExtra("STATUS")
        for (i in 0 until  binding.statusSpinEdit.adapter.count){
            if(binding.statusSpinEdit.getItemAtPosition(i).toString().contains(status.toString())){
                binding.statusSpinEdit.setSelection(i)
            }
        }
        //binding.statusSpinEdit.setText(intent.getStringExtra("STATUS"))

        displayDate()

        binding.btnCreateEdit.setOnClickListener {
            var nameSet = binding.activityEditET.text.toString()
            var dateSet = binding.dateEditET.text.toString()
            var statusSet = binding.statusSpinEdit.selectedItem.toString()

            val calendar: Calendar = Calendar.getInstance()
            val currentDate:String = DateFormat.getDateInstance().format(calendar.time)

            if (currentDate >= dateSet && statusSet == "Incomplete"){
                val intent = Intent(this,HomeFragment::class.java)
                intent.putExtra("Name",nameSet)
                intent.putExtra("Date",dateSet)
                intent.putExtra("Status","Incomplete")
                startActivityForResult(intent,requestCode)
            }else {
                val intent = Intent(this, HomeFragment::class.java)
                intent.putExtra("Name", nameSet)
                intent.putExtra("Date", dateSet)
                intent.putExtra("Status", statusSet)
                startActivityForResult(intent, requestCode)
            }
        }

    }
}
