package com.example.exame

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import com.example.exame.databinding.FragmentAddBinding

class AddFragment : Fragment() {

    // Engádese View Binding a este fragment
    private var _binding: FragmentAddBinding? = null
    private val binding: FragmentAddBinding
        get() = _binding!!

    //Úsase activityViewModels para compartir o mesmo ViewModel entre fragments, e polo tanto compartir os mesmos datos
    private val viewModel: GastoViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Ínflase o layout para este fragment, iníciase o binding e devólvese a vista do fragment
        _binding = FragmentAddBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Obsérvase o LiveData do ViewModel. É avisado cando cambia o gasto
        viewModel.gastoGardado.observe(viewLifecycleOwner) { gardado ->
            if (gardado) {
                // Context dá acceso a recursos, toasts, etc. Neste caso o Context é a activity asociada a este fragment
                Toast.makeText(requireContext(), "Gasto gardado correctamente!", Toast.LENGTH_SHORT).show()
                // Reacción ao evento: móstrase a mensaxe e límpase o formulario
                binding.editCantidade.text.clear()
                binding.editConcepto.text.clear()
            }
        }

        binding.btnGardar.setOnClickListener {
            val cantidade = binding.editCantidade.text.toString().toDoubleOrNull()
            //convértese o texto a Double con toDoubleOrNull para evitar crashes
            if (cantidade != null) {
                viewModel.engadirGasto(cantidade)
            }
        }
    }

    // limpeza para liberar a referencia á vista
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}