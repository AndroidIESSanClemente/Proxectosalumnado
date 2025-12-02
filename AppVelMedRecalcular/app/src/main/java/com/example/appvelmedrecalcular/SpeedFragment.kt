package com.example.appvelmedrecalcular

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import com.example.appvelmedrecalcular.databinding.FragmentSpeedBinding

class SpeedFragment : Fragment() {

    //variable nullable para evitar fugas de memoria (memory leaks)
    private var _binding: FragmentSpeedBinding? = null
    //propiedade segura para acceder ao binding
    private val binding: FragmentSpeedBinding
        get() = _binding!!

    //ViewModel compartido coa activity
    private val model: SpeedViewModel by viewModels(
        ownerProducer = { requireActivity() } //Serve para obter o ViewModel. Inicializa a propiedade model. O ownerProducer forza a que o ciclo de vida do ViewModel quede asociado á activity que contén o fragmento, no canto de ao ciclo de vida do Fragmento mesmo. No caso de que houbese mais fragmentos, compartirían a mesma instancia facilitando a comunicación e o intercambio de datos
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentSpeedBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Observadores de LiveData: para actualizar a UI automaticamente cando cambian os datos
        model.velocidadeKmH.observe(viewLifecycleOwner) {
            binding.txtSpeedKmH.text = "Velocidade en km/h: $it"
        }
        model.velocidadeMS.observe(viewLifecycleOwner) {
            binding.txtSpeedMS.text = "Velocidade en m/s: $it"
        }
        model.erro.observe(viewLifecycleOwner) { //mostra un Toast cando hai erro nos datos
            it?.let { msg -> Toast.makeText(requireContext(), msg, Toast.LENGTH_SHORT).show() }
        }

        // TextWatchers: cada vez que o usuario cambia un valor, actualízase o ViewModel
        binding.distancia.addTextChangedListener {
            model.distancia.value = it.toString()
            model.calcular() //recalcula automaticamente
        }
        binding.tempo.addTextChangedListener {
            model.tempo.value = it.toString()
            model.calcular()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        //vólvese null para evitar memory leaks
        _binding = null
    }
}