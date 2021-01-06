package com.example.myapplication

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.CreateTodoformBinding
import com.google.android.material.datepicker.MaterialDatePicker
import kotlinx.android.synthetic.main.create_todoform.*
import java.util.*

class CreateTodoFormActivity : AppCompatActivity() {

    //lateinit var option: Spinner
    var dayOr = 0
    var monthOr = 0
    var yearOr = 0

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.create_todoform)
        //datePicker()

        displayList()

        //DatePicker code
        val builder: MaterialDatePicker.Builder<*> = MaterialDatePicker.Builder.datePicker()
        builder.setTitleText("Select a Date")
        val picker:MaterialDatePicker<*> = builder.build()
        dateET.setOnClickListener {
            picker.show(supportFragmentManager,picker.toString())
        }
        picker.addOnPositiveButtonClickListener {
            dateET.setText(picker.headerText)
        }

    }

    private fun displayList(){

        val binding = CreateTodoformBinding.inflate(layoutInflater)


        binding.btnCreate.setOnClickListener {
            var nameSet = binding.activityET.text.toString()
            var dateSet = binding.dateET.text.toString()
            var statusSet = binding.statusSpin.text.toString()

            val intent = Intent(this,HomeScreenActivity::class.java)
            intent.putExtra("Name",nameSet)
            intent.putExtra("Date",dateSet)
            intent.putExtra("Status",statusSet)
            startActivity(intent)
        }
        setContentView(binding.root)
    }

}


