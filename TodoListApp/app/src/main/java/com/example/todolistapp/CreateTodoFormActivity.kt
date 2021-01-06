package com.example.todolistapp

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.todolistapp.R
import com.example.todolistapp.databinding.ActivityCreateTodoFormBinding
import com.example.todolistapp.ui.home.HomeFragment
import com.google.android.material.datepicker.MaterialDatePicker
import kotlinx.android.synthetic.main.activity_create_todo_form.*
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
    val binding = ActivityCreateTodoFormBinding.inflate(layoutInflater)

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_todo_form)
        setContentView(binding.root)
        //datePicker()
        val bundle:Bundle? = intent.extras
            if (bundle?.getString("some")!=null){
                //Toast.makeText(this,"activity",Toast.LENGTH_LONG).show()
            }

        datePicker()

        displayList()

        //spinner disabling
        //var result = findViewById<>()
        var spinner : Spinner
        spinner= findViewById<Spinner>(R.id.statusSpin)
        spinner.isEnabled.takeIf { false }

    }

    fun datePicker(){
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

    @RequiresApi(Build.VERSION_CODES.O)
    private fun displayList(){

        binding.btnCreate.setOnClickListener {
            var nameSet = binding.activityET.text.toString()
            var dateSet = binding.dateET.text.toString()
            var statusSet = binding.statusSpin.selectedItem.toString()

            val calendar:Calendar = Calendar.getInstance()
            val currentDate:String = DateFormat.getDateInstance().format(calendar.time)

            val manager:FragmentManager = supportFragmentManager
            val homeFragment:HomeFragment = HomeFragment()
            val transaction:FragmentTransaction = manager.beginTransaction()
            val bundle:Bundle? = intent.extras

            if (currentDate >= dateSet){
                val intent = Intent(this,HomeFragment::class.java)
                    intent.putExtra("Name",nameSet)
                    intent.putExtra("Date",dateSet)
                    intent.putExtra("Status","Incomplete")
                startActivityForResult(intent, requestCode)
            }else {
                val intent = Intent(this,HomeFragment::class.java)
                intent.putExtra("Name",nameSet)
                intent.putExtra("Date",dateSet)
                intent.putExtra("Status",statusSet)
                startActivityForResult(intent, requestCode)
            }
        }
    }

}


