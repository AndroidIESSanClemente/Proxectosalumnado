package com.example.IMCCalculator

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Button
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs

class ResultIMCFragment : Fragment() {
        private lateinit var tvResult: TextView
        private lateinit var tvIMC: TextView
        private lateinit var tvDescription: TextView
        private lateinit var btnRecalculate: Button

    private val args: ResultIMCFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_result_i_m_c, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val result: Double = args.imcResult.toDouble()

        initComponents(view)
        initUI(result)
        btnRecalculate.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun initComponents(view: View){
        tvIMC = view.findViewById(R.id.tvIMC)
        tvResult = view.findViewById(R.id.tvResult)
        tvDescription = view.findViewById(R.id.tvDescription)
        btnRecalculate = view.findViewById(R.id.btnRecalculate)
    }

    private fun initUI(result: Double){
        tvIMC.text = String.format("%.2f", result)

        when(result){
            in 0.00..18.50 -> {//Baixo peso
                tvResult.text = getString(R.string.title_baixo_peso)
                tvResult.setTextColor(ContextCompat.getColor(requireContext(), R.color.color_baixo))
                tvDescription.text = getString(R.string.description_baixo_peso)
            }
            in 18.51..24.99 -> {//Peso normal
                tvResult.text = getString(R.string.title_peso_normal)
                tvResult.setTextColor(ContextCompat.getColor(requireContext(), R.color.color_normal))
                tvDescription.text = getString(R.string.description_peso_normal)
            }
            in 25.00..29.99 -> {//Sobrepeso
                tvResult.text = getString(R.string.title_sobrepeso)
                tvResult.setTextColor(ContextCompat.getColor(requireContext(), R.color.color_sobrepeso))
                tvDescription.text = getString(R.string.description_sobrepeso)
            }
            in 30.00..99.00 -> {//Obesidade
                tvResult.text = getString(R.string.title_obesidade)
                tvResult.setTextColor(ContextCompat.getColor(requireContext(), R.color.color_obesidade))
                tvDescription.text = getString(R.string.description_obesidade)
            }
            else ->{//error
                tvIMC.text = getString(R.string.error)
                tvResult.text = getString(R.string.error)
                tvDescription.text = getString(R.string.error)
            }
        }
    }
}