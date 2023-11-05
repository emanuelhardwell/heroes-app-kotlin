package com.emanuel.mysecondapp.todo

import android.content.res.ColorStateList
import android.graphics.Paint
import android.view.View
import android.widget.CheckBox
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.emanuel.mysecondapp.R

class TasksViewHolder(view: View): RecyclerView.ViewHolder(view) {

    private var cardViewTask: CardView = view.findViewById(R.id.cardViewTask)
    private var textViewTask: TextView = view.findViewById(R.id.textViewTask)
    private var checkBoxTask: CheckBox = view.findViewById(R.id.checkBoxTask)


    fun render(task: Task){
        if (task.isSelected){
            textViewTask.paintFlags= textViewTask.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
        }else{
            textViewTask.paintFlags= textViewTask.paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
        }

        textViewTask.text= task.name
        checkBoxTask.isChecked= task.isSelected

        val color= when(task.category){
            TaskCategory.Business -> R.color.todo_business_category
            TaskCategory.Other -> R.color.todo_other_category
            TaskCategory.Personal -> R.color.todo_personal_category
        }

        checkBoxTask.buttonTintList= ColorStateList.valueOf(ContextCompat.getColor(checkBoxTask.context, color))

    }
}