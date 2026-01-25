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

    private var _binding: FragmentAddBinding? = null
    private val binding: FragmentAddBinding
        get() = _binding!!

    //Úsase activityViewModels para compartir o mesmo ViewModel entre fragments
    private val viewModel: GastoViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentAddBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Obsérvase cando o ViewModel di que se gardou correctamente
        viewModel.gastoGardado.observe(viewLifecycleOwner) { gardado ->
            if (gardado) {
                Toast.makeText(requireContext(), "Gasto gardado correctamente!", Toast.LENGTH_SHORT).show()
                // Importante: indécaselle ao ViewModel que xa se mostrou a mensaxe
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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}