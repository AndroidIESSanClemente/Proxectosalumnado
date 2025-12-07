package com.example.apptarefas

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.apptarefas.databinding.FragmentSummaryBinding
import kotlin.getValue


class SummaryFragment : Fragment() {

    private var _binding: FragmentSummaryBinding? = null
    private val binding: FragmentSummaryBinding
        get() = _binding!!

    //ViewModel compartido entre fragmentos
    private val model: TaskViewModel by viewModels (
        ownerProducer = {requireActivity()}
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentSummaryBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Observamos a lista de tarefas, actualizamos o resumo cada vez que cambie
        model.tasks.observe(viewLifecycleOwner){
            updateSummary()
        }
    }

    //Actualiza todos os TextViews co resumo actual
    private fun updateSummary(){
        binding.textTotalTasks.text = "Total tarefas: ${model.totalTasks()}"
        binding.textTotalCompleted.text = "Completadas: ${model.totalCompleted()}"
        binding.textTotalLow.text = "Baixa: ${model.totalByPriority(Priority.Baixa)}"
        binding.textTotalMedium.text = "Media: ${model.totalByPriority(Priority.Media)}"
        binding.textTotalHigh.text = "Alta: ${model.totalByPriority(Priority.Alta)}"
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}