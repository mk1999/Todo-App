package com.example.todolistapplication

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.annotation.RequiresApi
import com.example.todolistapplication.databinding.CreateTodoformBinding
import com.google.android.material.datepicker.MaterialDatePicker
import kotlinx.android.synthetic.main.create_todoform.*
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.util.*

class CreateTodoFormActivity : AppCompatActivity() {

    //lateinit var option: Spinner
    var dayOr = 0
    var monthOr = 0
    var yearOr = 0

    private val requestCode = 1
    lateinit var option :Spinner


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.create_todoform)
        datePicker()

        displayList()

        //spinner disabling
        //var result = findViewById<>()
        var spinner : Spinner
        spinner= findViewById<Spinner>(R.id.statusSpin)
        spinner.isEnabled.takeIf { false }

        //DatePicker code


    }

    fun datePicker(){
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

    @RequiresApi(Build.VERSION_CODES.O)
    private fun displayList(){
        val binding = CreateTodoformBinding.inflate(layoutInflater)

        binding.btnCreate.setOnClickListener {
            var nameSet = binding.activityET.text.toString()
            var dateSet = binding.dateET.text.toString()
            var statusSet = binding.statusSpin.selectedItem.toString()

            val calendar:Calendar = Calendar.getInstance()
            val currentDate:String = DateFormat.getDateInstance().format(calendar.time)

            if (currentDate >= dateSet){
                val intent = Intent(this,MainActivity::class.java)
                intent.putExtra("Name",nameSet)
                intent.putExtra("Date",dateSet)
                intent.putExtra("Status","Incomplete")
                startActivityForResult(intent,requestCode)
            }else {
                val intent = Intent(this, MainActivity::class.java)
                intent.putExtra("Name", nameSet)
                intent.putExtra("Date", dateSet)
                intent.putExtra("Status", statusSet)
                startActivityForResult(intent, requestCode)
            }
        }
        setContentView(binding.root)
    }

}


