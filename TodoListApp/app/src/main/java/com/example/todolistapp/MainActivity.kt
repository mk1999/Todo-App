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
    val todo = ArrayList<TodoData>()
    val adapater = TodoListAdapater(todo,this)
    val binding = ActivityMainBinding.inflate(layoutInflater)
    
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setContentView(binding.root)
        navigatebtn()
        //supportActionBar?.setDisplayHomeAsUpEnabled(true)
        //supportFragmentManager.beginTransaction().replace(R.id.mainActivityRecycler,HomeFragment()).commit()

        
    }

   /* override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home){
            finish()
        }
        return super.onOptionsItemSelected(item)
    }*/

    fun navigatebtn(){
        
         binding.fabIconAdd.setOnClickListener {
            //Toast.makeText(this,"Clicked",Toast.LENGTH_LONG).show()
            var intent = Intent(this,CreateTodoFormActivity::class.java)
            startActivityForResult(intent,requestCode)
         }

    }
    
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        val recyclerView = findViewById<RecyclerView>(R.id.todoListRV)
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = adapater
        recyclerView.layoutManager = LinearLayoutManager(this)

        if(data!=null) {
            if (resultCode == RESULT_OK) {
                var name = data!!.getStringExtra("Name")
                var date = data!!.getStringExtra("Date")
                var status = data!!.getStringExtra("Status")
                todo.add(TodoData(name.toString(),date.toString(),status.toString()))
                adapater.notifyDataSetChanged()
            }
        }

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




