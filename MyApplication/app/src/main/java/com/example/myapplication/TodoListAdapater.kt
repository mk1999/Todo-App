package com.example.myapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.todo_items.view.*
import org.w3c.dom.Text

class TodoListAdapater(var todoList:ArrayList<TodoData>, var ontodoItemClickListener:OntodoItemClickListener) : RecyclerView.Adapter<TodoListAdapater.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameView = itemView.activityNameTV
        val dateView = itemView.dateTV
        val statusView = itemView.statusTV

        fun initialise(item: TodoData, action: OntodoItemClickListener) {
            nameView.text = item.name
            dateView.text = item.date
            statusView.text = item.status

            itemView.setOnClickListener {
                action.onItemClickListener(item, adapterPosition)
            }
        }
    }

    interface OntodoItemClickListener {
        fun onItemClickListener(item: TodoData, position: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val result = LayoutInflater.from(parent.context).inflate(R.layout.todo_items, parent, false)
        return ViewHolder(result)

    }

    /*fun addTodo(todoData: TodoData){
        todoList.add(todoData)
        notifyItemInserted(todoList.size-1)
    }*/
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        /* val todo = todoList[position]
            holder.nameView.text = todo.name
            holder.dateView.text = todo.date
            holder.statusView.text = todo.status*/
        holder.initialise(todoList.get(position), ontodoItemClickListener)


    }

    override fun getItemCount(): Int {
        return todoList.size
    }
}


