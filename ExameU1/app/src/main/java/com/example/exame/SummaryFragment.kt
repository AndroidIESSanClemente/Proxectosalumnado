package com.example.exame

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.exame.databinding.FragmentSummaryBinding

//Fragment que mostra o total de gastos e permite limpar os datos
class SummaryFragment : Fragment() {

    private var _binding: FragmentSummaryBinding? = null
    private val binding: FragmentSummaryBinding
        get() = _binding!!

    private val viewModel: GastoViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentSummaryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Obsérvase o LiveData para actualizar a interface automaticamente
        viewModel.totalGasto.observe(viewLifecycleOwner) { total ->
            binding.textTotal.text = "Total gastado: ${String.format("%.2f €", total)}"
        }
        //Botón flotante para limpar
        binding.fabDelete.setOnClickListener {
            viewModel.limparGasto()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}