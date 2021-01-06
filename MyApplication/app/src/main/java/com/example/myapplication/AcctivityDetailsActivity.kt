package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.myapplication.databinding.AcctivityDetailsBinding

class AcctivityDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.acctivity_details)

        val binding = AcctivityDetailsBinding.inflate(layoutInflater)
        binding.nameDetTV.text = intent.getStringExtra("ACTIVITYNAME")
        binding.dateDetTv.text = intent.getStringExtra("DEADLINE")
        binding.statusDelTV.text = intent.getStringExtra("STATUS")
        setContentView(binding.root)
    }
}