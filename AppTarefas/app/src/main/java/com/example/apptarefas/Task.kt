package com.example.apptarefas;

//Data class que representa unha tarefa
data class Task (
    //Nome da tarefa escrita polo usuario
    val name: String,
    //Prioridade seleccionada (Baixa, Media ou Alta)
    val priority: Priority,
    //Indica se a tarefa está completada (por defecto, non o está)
    var completed: Boolean = false
)

//Enumeración que define os tres niveis de prioridade posibles
enum class Priority{
    Baixa, Media, Alta
}