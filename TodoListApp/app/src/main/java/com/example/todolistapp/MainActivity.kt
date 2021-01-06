package com.example.todolistapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todolistapp.*
import com.example.todolistapp.databinding.ActivityMainBinding
import com.example.todolistapp.ui.home.HomeFragment
import com.google.gson.Gson
import com.google.gson.JsonObject
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.todo_items.*
import org.json.JSONObject
import kotlin.random.Random

class MainActivity : AppCompatActivity(), TodoListAdapater.OntodoItemClickListener {

    private val requestCode = 1

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportFragmentManager.beginTransaction().replace(R.id.mainActivityRecycler,HomeFragment()).commit()

        getTodoList()
    }

   /* override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home){
            finish()
        }
        return super.onOptionsItemSelected(item)
    }*/

    fun getTodoList(){
        val binding = ActivityMainBinding.inflate(layoutInflater)

        val recyclerView = binding.todoListRV
        val todo = ArrayList<TodoData>()
        val adapater = TodoListAdapater(todo,this)
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = adapater
        recyclerView.layoutManager = LinearLayoutManager(this)

        val intent = intent
        var name = intent.getStringExtra("Name")
        var date = intent.getStringExtra("Date")
        var status = intent.getStringExtra("Status")
        val index:Int = Random.nextInt(2)

        name?.let {
            date?.let {
                status?.let {
                    val newItem = TodoData(name,date,status)
                    todo.add(index,newItem)
                    adapater.notifyDataSetChanged()
                    /*todo.add(TodoData(name.toString(), date.toString(), status.toString()))
                    adapater.notifyDataSetChanged()*/
                    //TodoData(name.toString(), date.toString(), status.toString())
                    //adapater.notifyData(todo)
                }
            }
        }

        /* if (name.toString().isNotEmpty() && date.toString().isNotEmpty() && status.toString().isNotEmpty()){
            todo.add(TodoData(name.toString(), date.toString(), status.toString()))
        }*/

        binding.fabIconAdd.setOnClickListener {
            var intent = Intent(this, CreateTodoFormActivity::class.java)
            startActivityForResult(intent,requestCode)
        }
        setContentView(binding.root)

    }

    override fun onItemClickListener(item: TodoData, position: Int) {
        //Toast.makeText(this,item.name,Toast.LENGTH_LONG).show()
        val intent = Intent(this, AcctivityDetailsActivity::class.java)
        intent.putExtra("ACTIVITYNAME", item.name)
        intent.putExtra("DEADLINE", item.date)
        intent.putExtra("STATUS", item.status)
        startActivityForResult(intent,requestCode)

        btnedit.setOnClickListener {
            val intent = Intent(this, EditpageActivity::class.java)
            intent.putExtra("ACTIVITYNAME", item.name)
            intent.putExtra("DEADLINE", item.date)
            intent.putExtra("STATUS", item.status)
            startActivityForResult(intent,requestCode)
        }

    }

}




