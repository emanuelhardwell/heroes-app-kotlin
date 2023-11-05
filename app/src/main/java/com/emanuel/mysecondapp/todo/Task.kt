package com.emanuel.mysecondapp.todo

data class Task(val name: String, val category: TaskCategory, var isSelected: Boolean = false) {

}