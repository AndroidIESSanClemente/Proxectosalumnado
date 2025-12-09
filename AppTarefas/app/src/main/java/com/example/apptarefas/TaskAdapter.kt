package com.example.apptarefas

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.apptarefas.databinding.ItemTaskBinding

//Adapter que se encarga de mostrar a lista de tarefas en vistas que se mostrarán no Recyclerview
// Tamén recibe unha función lammbda para avisar cando unha tarefa se marca/desmarca
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

    //Número de elementos (tamaño da lista). RecyclerView usa este dato para saber cantos elementos debe mostrar
    override fun getItemCount(): Int = tasks.size

    // Este métod chámase cada vez que unha vista (ViewHolder) se mostra na pantalla
    // Asigna/rechea cada ítem con datos
    // Como o RecyclerView recicla vista, chámase continuamente
    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val task = tasks[position]
        // "with" permite acceder directamente ás vistas do binding
        with (holder.binding){
            //Mostra o nome da tarefa
            textTask.text = task.name
            //Mostra a prioridade como texto, .name devolve o nome do enum (Baixa, Media, Alta)
            chipPriority.text = task.priority.name

            // Cambiar a cor do chip segundo a prioridade da tarefa
            val colorRes = when (task.priority) {
                Priority.Baixa -> R.color.priority_low
                Priority.Media -> R.color.priority_medium
                Priority.Alta -> R.color.priority_high
            }
            chipPriority.setChipBackgroundColorResource(colorRes)
            //Cor do texto do chip, en branco
            chipPriority.setTextColor(root.context.getColor(R.color.white))

            // Previr que se dispare o listener ao reciclar vistas, para o cal se quita temporalmente o listener
            chipCompleted.setOnCheckedChangeListener(null)
            // Indicar no chip se a tarefa está completada
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
        //notifícase ao RecyclerView que toda a lista cambiou
        notifyDataSetChanged()
    }
}