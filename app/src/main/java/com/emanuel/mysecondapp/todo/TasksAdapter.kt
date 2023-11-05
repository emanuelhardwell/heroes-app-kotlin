package com.emanuel.mysecondapp.todo

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.emanuel.mysecondapp.R

class TasksAdapter(var tasks: List<Task>, private val onSelectedTask : (Int) -> Unit ): RecyclerView.Adapter<TasksViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TasksViewHolder {
        // TODO: Actualizar el item 
        val view= LayoutInflater.from(parent.context).inflate(R.layout.item_task, parent,false)
        return TasksViewHolder(view)
    }

    override fun onBindViewHolder(holder: TasksViewHolder, position: Int) {
        holder.render(tasks[position])
        // El ItemView es toda la celda del conjunto de 'item task'
        holder.itemView.setOnClickListener {
            onSelectedTask(position)
        }
    }

    override fun getItemCount(): Int {
        return tasks.size
    }

}