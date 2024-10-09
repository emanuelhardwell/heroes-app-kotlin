package com.emanuel.mysecondapp.todo

// Si todos mis Objetos tienen las mismas propiedadades, solo se le añade el parametro al padre
// Si cada Objeto tienen diferentes propiedadades, se debe cambiar el Object a DataClass y pasarle su parametro especifico
sealed class TaskCategory( var isSelected: Boolean = true) {
    object Personal: TaskCategory()
    object Business: TaskCategory()
    object Other: TaskCategory()
}