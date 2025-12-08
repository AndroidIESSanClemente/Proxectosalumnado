package com.example.apptarefas

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

//Créase unha clase que herda da clase ViewModel
class TaskViewModel : ViewModel() {

    //LiveData que contén unha lista de tarefas
    // MutableLiveData = pódese modificar desde o ViewModel
    // LiveData = só se pode ler desde os fragments
    private val _tasks = MutableLiveData<MutableList<Task>>(mutableListOf())
    //Exposición segura da lista (os fragments non poden modificala directamente)
    val tasks: LiveData<MutableList<Task>> = _tasks
    //Engadir unha nova tarefa
    fun addTask(task: Task){
        _tasks.value = (_tasks.value ?: mutableListOf()).apply { add(task) }
    }

    //Marcar/desmarcar unha tarefa tarefa completada
    fun setCompleted(task: Task, completed: Boolean) {
        _tasks.value?.find { it == task }?.completed = completed
        _tasks.value = _tasks.value
    }

    //Métodos para resumo
    fun totalTasks(): Int = _tasks.value?.size ?: 0
    //Cantas tarefas están marcadas como completadas
    fun totalCompleted(): Int = _tasks.value?.count {it.completed} ?: 0
    //Cantas tarefas teñen unha prioridde concreta
    fun totalByPriority(priority: Priority): Int =
        _tasks.value?.count {it.priority == priority} ?: 0
}