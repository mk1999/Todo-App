package com.example.todolistapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.todolistapplication.databinding.AcctivityDetailsBinding
import androidx.databinding.DataBindingUtil

class AcctivityDetailsActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.acctivity_details)

        displayDetails()
    }

    fun displayDetails(){
        val binding = AcctivityDetailsBinding.inflate(layoutInflater)
        binding.nameDetTV.text = intent.getStringExtra("ACTIVITYNAME")
        binding.dateDetTv.text = intent.getStringExtra("DEADLINE")
        binding.statusDelTV.text = intent.getStringExtra("STATUS")
        setContentView(binding.root)
    }
}