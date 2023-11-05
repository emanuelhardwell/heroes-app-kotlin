package com.emanuel.mysecondapp.todo

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.emanuel.mysecondapp.R

class CategoriesAdapter(private val categories: List<TaskCategory>, private var onSelectedCategory: (Int) -> Unit ): RecyclerView.Adapter<CategoriesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriesViewHolder {
        val view= LayoutInflater.from(parent.context).inflate(R.layout.item_task_category, parent, false)
        return CategoriesViewHolder(view)
    }

    override fun onBindViewHolder(holder: CategoriesViewHolder, position: Int) {
        //holder.render(categories[position])
        holder.render(categories[position], onSelectedCategory)
    }

    override fun getItemCount(): Int {
        return categories.size
    }

    //Se puede retornar directamente el resultado en una funcion
    //override fun getItemCount() = categories.size

}