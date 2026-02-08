package com.example.exame

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.exame.databinding.FragmentSummaryBinding

//Fragment que mostra o total de gastos e permite limpar os datos mediante o botón flotante
class SummaryFragment : Fragment() {

    // Engádese ViewBinding para acceder de xeito seguro ás vistas
    private var _binding: FragmentSummaryBinding? = null
    private val binding: FragmentSummaryBinding
        get() = _binding!!

    //Úsase activityViewModels para compartir o mesmo ViewModel entre fragments, e polo tanto compartir os mesmos datos
    private val viewModel: GastoViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Ínflase o layout para este fragment, iníciase o binding e devólvese a vista do fragment
        _binding = FragmentSummaryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Obsérvase o LiveData para actualizar a interface automaticamente. //
        // Observa só mentras a vista do frament exista (viewLifecycleOwner).
        // Cando se chame a onDestroyView(), deixará observar automaticamente
        viewModel.totalGasto.observe(viewLifecycleOwner) { total ->
            // Formatéase a 2 decimais e co símbolo do euro
            binding.textTotal.text = "Total gastado: ${String.format("%.2f €", total)}"
        }
        //Botón flotante para limpar os gastos. Chámase a limparGasto() no ViewModel. Como o fragment está observando a interface actualízase soa se necesidade de refrescar manualmente
        binding.fabDelete.setOnClickListener {
            viewModel.limparGasto()
        }
    }

    // limpeza para liberar a referencia á vista
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}