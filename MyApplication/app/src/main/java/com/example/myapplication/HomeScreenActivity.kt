package com.example.myapplication

import android.content.Intent
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.*
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.databinding.HomeScreenBinding
import kotlinx.android.synthetic.main.todo_items.*

class HomeScreenActivity : AppCompatActivity(), TodoListAdapater.OntodoItemClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.home_screen)

        getTodoList()

    }

    fun getTodoList(){
        val binding = HomeScreenBinding.inflate(layoutInflater)

        val recyclerView = binding.todoListRV
        recyclerView.layoutManager = LinearLayoutManager(this)
        val todo = ArrayList<TodoData>()

        val intent = intent
        var name = intent.getStringExtra("Name")
        var date = intent.getStringExtra("Date")
        var status = intent.getStringExtra("Status")

        todo.add(TodoData("Doctor Appointment","2 Jan 2021","Created"))
        todo.add(TodoData("Task","3 Feb 2021","Created"))

        if (name.toString().isNotEmpty() && date.toString().isNotEmpty() && status.toString().isNotEmpty()){
            todo.add(TodoData(name.toString(), date.toString(), status.toString()))
        }
        val adapater = TodoListAdapater(todo,this)
        recyclerView.adapter = adapater

        binding.fabIconAdd.setOnClickListener {
            var intent = Intent(this,CreateTodoFormActivity::class.java)
            startActivity(intent)
        }

        setContentView(binding.root)
    }

    override fun onItemClickListener(item: TodoData, position: Int) {
        //Toast.makeText(this,item.name,Toast.LENGTH_LONG).show()
        val intent = Intent(this, AcctivityDetailsActivity::class.java)
        intent.putExtra("ACTIVITYNAME", item.name)
        intent.putExtra("DEADLINE", item.date)
        intent.putExtra("STATUS", item.status)
        startActivity(intent)

        btnedit.setOnClickListener {
            val intent = Intent(this,EditpageActivity::class.java)
            intent.putExtra("ACTIVITYNAME", item.name)
            intent.putExtra("DEADLINE", item.date)
            intent.putExtra("STATUS", item.status)
            startActivity(intent)
        }

    }


}


