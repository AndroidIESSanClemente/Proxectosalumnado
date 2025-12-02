package com.example.appvelocidademedia

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.appvelocidademedia.databinding.FragmentInputBinding

class InputFragment : Fragment() {

    private var _binding: FragmentInputBinding? = null
    private val binding: FragmentInputBinding
        get() = _binding!!

    private val model: SpeedViewModel by viewModels(
        ownerProducer = { requireActivity() }   // Compartido por ambos fragmentos
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentInputBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
        //poderíase poñer directamente return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // TextWatchers para recalcular automaticamente
        binding.distancia.addTextChangedListener {
            model.distancia.value = it.toString()
        }
        binding.tempo.addTextChangedListener {
            model.tempo.value = it.toString()
        }

        // Botón para ir ao fragmento de resultados
        binding.btnResult.setOnClickListener { model.calcular()

            if (model.erro.value != null) {
                Toast.makeText(requireContext(), model.erro.value, Toast.LENGTH_SHORT).show()
            } else {
                findNavController().navigate(R.id.action_inputFragment_to_resultFragment)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}