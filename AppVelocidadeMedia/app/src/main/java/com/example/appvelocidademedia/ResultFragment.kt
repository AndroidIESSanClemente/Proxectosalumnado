package com.example.appvelocidademedia

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.example.appvelocidademedia.databinding.FragmentResultBinding

class ResultFragment : Fragment() {

    private var _binding: FragmentResultBinding? = null
    private val binding: FragmentResultBinding
        get() = _binding!!

    private val model: SpeedViewModel by viewModels(
        ownerProducer = { requireActivity() }   // Compartido por ambos fragmentos
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentResultBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Mostránse os resultados ou un erro automaticamente grazas a LiveData
        model.velocidadeKmH.observe(viewLifecycleOwner) {
            binding.txtSpeedKmH.text = it
        }
        model.velocidadeMS.observe(viewLifecycleOwner) {
            binding.txtSpeedMS.text = it
        }
        model.erro.observe(viewLifecycleOwner) {
            binding.txtErro.text = it ?: ""
        }

        //Botón para iniciar un novo cálculo
        binding.btnNewCalc.setOnClickListener {
            model.reset() //limpa os valores
            view.findNavController().navigate(R.id.action_resultFragment_to_inputFragment)
        }
    }

        override fun onDestroyView() {
            super.onDestroyView()
            _binding = null
        }
    }