package com.example.todolistapp.ui.home

import android.content.Intent
import android.content.Intent.getIntent
import android.content.Intent.getIntentOld
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.DataBindingUtil
import androidx.databinding.DataBindingUtil.setContentView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todolistapp.*
import com.example.todolistapp.databinding.ActivityMainBinding
import com.example.todolistapp.databinding.FragmentHomeBinding
import com.example.todolistapp.ui.gallery.GalleryViewModel
import kotlinx.android.synthetic.main.todo_items.*
import java.util.EnumSet.of
import java.util.List.of
import java.util.Optional.of
import kotlin.random.Random

class HomeFragment : Fragment(), TodoListAdapater.OntodoItemClickListener  {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var homeViewModel: HomeViewModel
    private val requestCode = 1

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        binding.fabIconAdd.setOnClickListener {
            val intent = Intent(activity, CreateTodoFormActivity::class.java)
            intent.putExtra("some", "some Data")
            startActivityForResult(intent, requestCode)
        }
        return binding.root
    }


  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

      val todo = ArrayList<TodoData>()
      val adapater = TodoListAdapater(todo,this)
      binding.todoListRV.setHasFixedSize(true)

      binding.todoListRV.layoutManager = LinearLayoutManager(activity)

      val intent = activity?.intent
      var name = intent?.getStringExtra("Name")
      var date = intent?.getStringExtra("Date")
      var status = intent?.getStringExtra("Status")
     var index:Int = adapater.itemCount

      name?.let {
          date?.let {
              status?.let {

                  var newItem = TodoData(name,date,status)
                   todo.add(index-1,newItem)
                   adapater.notifyDataSetChanged()

              }
          }
      }
      binding.todoListRV.adapter = adapater


    }


    override fun onItemClickListener(item: TodoData, position: Int) {
        //Toast.makeText(this,item.name,Toast.LENGTH_LONG).show()
        val intent = Intent(activity, AcctivityDetailsActivity::class.java)
        intent.putExtra("ACTIVITYNAME", item.name)
        intent.putExtra("DEADLINE", item.date)
        intent.putExtra("STATUS", item.status)
        startActivityForResult(intent,requestCode)

        btnedit.setOnClickListener {
            val intent = Intent(activity, EditpageActivity::class.java)
            intent.putExtra("ACTIVITYNAME", item.name)
            intent.putExtra("DEADLINE", item.date)
            intent.putExtra("STATUS", item.status)
            startActivityForResult(intent,requestCode)
        }
    }
}
