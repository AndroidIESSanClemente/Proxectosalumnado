package com.example.apptarefas

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.apptarefas.databinding.ItemTaskBinding

//Adapter que se encarga de mostrar a lista de tarefas
class TaskAdapter (
    private var tasks: List<Task>,
    private val onCheckedChanged: (Task, Boolean) -> Unit
) : RecyclerView.Adapter<TaskAdapter.TaskViewHolder>()
{
    //ViewHolder que contén as vistas do layout item_task.xml
    class TaskViewHolder (val binding: ItemTaskBinding)
        : RecyclerView.ViewHolder(binding.root)

    //Infla o layout da fila e crea o ViewHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val binding = ItemTaskBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TaskViewHolder(binding)
    }

    //Número de elementos (tamaño da lista)
    override fun getItemCount(): Int = tasks.size

    //Asigna os datos dunha tarefa ás vistas da fila
    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val task = tasks[position]
        with (holder.binding){
            //Mlostrar o nome da tarefa
            textTask.text = task.name
            chipPriority.text = task.priority.name

            // Cambiar cor segundo prioridade
            val colorRes = when (task.priority) {
                Priority.Baixa -> R.color.priority_low
                Priority.Media -> R.color.priority_medium
                Priority.Alta -> R.color.priority_high
            }
            chipPriority.setChipBackgroundColorResource(colorRes)
            //Cor do texto do chip
            chipPriority.setTextColor(root.context.getColor(R.color.white))

            // Previr que se dispare o listener ao reciclar vistas
            chipCompleted.setOnCheckedChangeListener(null)
            // Indicar se a tarefa está completada
            chipCompleted.isChecked = task.completed
            // Listener para marcar/desmarcar tarefa
            chipCompleted.setOnCheckedChangeListener { _, checked ->
                onCheckedChanged(task, checked)
            }
        }

    }
    //Cando cambia a lista, actualízase o adaptador
    fun updateList(newTasks: List<Task>){
        tasks = newTasks
        notifyDataSetChanged()
    }

}