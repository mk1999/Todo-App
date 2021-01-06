package com.example.todolistapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.todolistapp.databinding.ActivityDetailsBinding
import androidx.databinding.DataBindingUtil
import com.example.todolistapp.R

class AcctivityDetailsActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        displayDetails()
    }

    fun displayDetails(){
        val binding = ActivityDetailsBinding.inflate(layoutInflater)
        val bundle:Bundle? = intent.extras
        if (bundle?.getString("ACTIVITYNAME")!=null) {

            binding.nameDetTV.text = bundle?.getString("ACTIVITYNAME")
            binding.dateDetTv.text = bundle?.getString("DEADLINE")
            binding.statusDelTV.text = bundle?.getString("STATUS")

            setContentView(binding.root)
        }
    }
}