package com.example.apptarefas

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.apptarefas.databinding.FragmentAddBinding
import kotlin.getValue

class AddFragment : Fragment() {

    private var _binding: FragmentAddBinding? = null
    private val binding: FragmentAddBinding
        get() = _binding!!

    private val model: TaskViewModel by viewModels (
        ownerProducer = {requireActivity()}
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentAddBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //botón para engadir tarefa
        // trim() serve para eliminar os espazos en branco ao inicio e ao final dunha cadea de texto
        binding.buttonAddTask.setOnClickListener {
            val name = binding.editTaskName.text.toString().trim()
            if (name.isEmpty()) {
                binding.editTaskName.error = "Escribe o nome da tarefa"
                return@setOnClickListener
            }
            //Obter prioridade seleccionada
            val selectedId = binding.chipGroupPriority.checkedChipId
            val priority = when (selectedId) {
                R.id.chip_low -> Priority.Baixa
                R.id.chip_medium -> Priority.Media
                R.id.chip_high -> Priority.Alta
                else -> Priority.Baixa
            }

            //Crear a tarefa e engadir ao ViewModel
            val task = Task(name, priority)
            model.addTask(task)

            //Limpar campos
            binding.editTaskName.text?.clear()
            binding.chipGroupPriority.clearCheck()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}