package com.example.apptarefas

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.apptarefas.databinding.FragmentListBinding
import kotlin.getValue

class ListFragment : Fragment() {

    private var _binding: FragmentListBinding? = null
    private val binding: FragmentListBinding
        get() = _binding!!

    //ViewModel compartido entre fragmentos usando o Activity como owner
    private val model: TaskViewModel by viewModels (
        ownerProducer = {requireActivity()}
    )
    //Adapter para o RecyclerView, inicializado mais adiante
    private lateinit var adapter: TaskAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentListBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //Créase o adapter de RecyclerView
        //O constructor recibe unha lista inicial baleira e un listener para cambios no estado "completado"
        adapter = TaskAdapter(emptyList()) {task, checked ->
            //Cando o usuario marca/desmarca unha tarefa como completada, actualízase o ViewModel
            model.setCompleted(task, checked)
        }

        // Configúrase o RecyclerView:
        // -LayoutManager lineal en vertical
        // -Adapter previamente creado
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = adapter

        //Obsérvase a lista de tarefas no ViewModel
        //Cando cambia, actualízase a lista do adapter para refrescar a UI
        model.tasks.observe(viewLifecycleOwner){list ->
            adapter.updateList(list)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}