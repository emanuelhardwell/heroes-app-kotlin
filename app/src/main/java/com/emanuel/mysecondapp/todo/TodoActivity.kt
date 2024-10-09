package com.emanuel.mysecondapp.todo

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.emanuel.mysecondapp.R
import com.google.android.material.floatingactionbutton.FloatingActionButton

class TodoActivity : AppCompatActivity() {

    private lateinit var recyclerViewCategories: RecyclerView
    private lateinit var categoriesAdapter: CategoriesAdapter
    private var categories= listOf(TaskCategory.Personal, TaskCategory.Business, TaskCategory.Other)

    private lateinit var recyclerViewTasks: RecyclerView
    private lateinit var tasksAdapter: TasksAdapter
    private var tasks = mutableListOf(
        Task("Business todo example", TaskCategory.Business),
        Task("Personal todo example", TaskCategory.Personal),
        Task("Other todo example", TaskCategory.Other)
    )

    private lateinit var fabAddTask: FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_todo)

        initComponents()
        initUI()
        initListeners()
    }

    private fun initListeners() {
        fabAddTask.setOnClickListener { showDialog() }
    }

    private fun showDialog() {
        val dialog= Dialog(this)
        dialog.setContentView(R.layout.dialog_task)


        val btnAddTask: Button = dialog.findViewById(R.id.btnAddTask)
        val editTextTask: EditText= dialog.findViewById(R.id.editTextTask)
        val radioGroupCategories: RadioGroup= dialog.findViewById(R.id.radioGroupCategories)

        dialog.show()

        btnAddTask.setOnClickListener {
            val currentTask= editTextTask.text.toString()

            if (currentTask.isNotEmpty()){
                val selectedId= radioGroupCategories.checkedRadioButtonId
                val radioButtonSelected: RadioButton = radioGroupCategories.findViewById(selectedId)
                val currentlyCategory: TaskCategory = when(radioButtonSelected.text){
                    // Se puede validar el string directamente, PERO nos podemos equivocar al escribirlo
                    //"Negocios" -> TaskCategory.Business
                    //"Personal" -> TaskCategory.Personal
                    getString(R.string.todo_dialog_category_business) -> TaskCategory.Business
                    getString(R.string.todo_dialog_category_personal) -> TaskCategory.Personal
                    else -> TaskCategory.Other
                }
                tasks.add(Task(currentTask, currentlyCategory))
                updateTasks()
                dialog.hide()
            }

        }
        //dialog.show()
    }

    private fun initComponents() {
        recyclerViewCategories= findViewById(R.id.recyclerViewCategories)
        recyclerViewTasks= findViewById(R.id.recyclerViewTasks)
        fabAddTask= findViewById(R.id.fabAddTask)
    }

    private fun initUI() {
        //categoriesAdapter= CategoriesAdapter(categories)
        categoriesAdapter= CategoriesAdapter(categories){position -> onSelectedCategoryItem(position) }
        recyclerViewCategories.layoutManager= LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        recyclerViewCategories.adapter= categoriesAdapter

        //tasksAdapter= TasksAdapter(tasks)

        // Las funciones LAMDA se pueden llamar de 3 formas --'Pasarla como segundo parametro'  -- ''ponerla despues de ser instanciada''  --  "ponerla despues de ser instanciada y llamarla directamente"
        //tasksAdapter= TasksAdapter(tasks, {position -> onSelectedTaskItem(position)})
        //tasksAdapter= TasksAdapter(tasks){ -> onSelectedTaskItem(it)}
        tasksAdapter= TasksAdapter(tasks){position -> onSelectedTaskItem(position)}
        recyclerViewTasks.layoutManager= LinearLayoutManager(this) //Cuando el LinearLayout va ser 'vertical' no es necesario especificarlo
        recyclerViewTasks.adapter= tasksAdapter
    }

    private fun onSelectedTaskItem(position: Int){
        tasks[position].isSelected= !tasks[position].isSelected
        updateTasks()
    }

    /*private fun updateTasks() {
        tasksAdapter.notifyDataSetChanged()
    }*/
    private fun updateTasks() {
        val categories: List<TaskCategory> = categories.filter{ it.isSelected }
        val newTasks: List<Task> = tasks.filter { categories.contains(it.category) }
        tasksAdapter.tasks= newTasks
        tasksAdapter.notifyDataSetChanged()
    }

    private fun onSelectedCategoryItem(position: Int){
        categories[position].isSelected= !categories[position].isSelected
        categoriesAdapter.notifyItemChanged(position)
        updateTasks()
    }

}