package com.emanuel.mysecondapp.todo

import android.view.View
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.emanuel.mysecondapp.R

class CategoriesViewHolder(view: View): RecyclerView.ViewHolder(view) {

    private  var cardViewCategory: CardView = view.findViewById(R.id.cardViewCategory)
    private  var textViewCategoryName: TextView = view.findViewById(R.id.textViewCategoryName)
    private  var dividerCategory: View = view.findViewById(R.id.dividerCategory)


    //textViewCategoryName.text= "Ejemplo1"

    fun render(taskCategory: TaskCategory, onSelectedCategory: (Int) -> Unit){


        val color = if (taskCategory.isSelected) {
            R.color.todo_background_card
        } else {
            R.color.todo_background_disabled
        }

        cardViewCategory.setCardBackgroundColor(
            ContextCompat.getColor(
                cardViewCategory.context,
                color
            )
        )

        itemView.setOnClickListener {
            onSelectedCategory(layoutPosition)
        }

        when(taskCategory){
            TaskCategory.Business -> {
                textViewCategoryName.text= "Business"
                dividerCategory.setBackgroundColor(ContextCompat.getColor(dividerCategory.context ,R.color.todo_business_category))
            }
            TaskCategory.Other -> {
                textViewCategoryName.text= "Other"
                dividerCategory.setBackgroundColor(ContextCompat.getColor(dividerCategory.context ,R.color.todo_other_category))
            }
            TaskCategory.Personal -> {
                textViewCategoryName.text= "Personal"
                dividerCategory.setBackgroundColor(ContextCompat.getColor(dividerCategory.context ,R.color.todo_personal_category))
            }
        }
    }

}