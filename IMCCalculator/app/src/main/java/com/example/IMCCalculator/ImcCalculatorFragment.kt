package com.example.IMCCalculator

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.slider.RangeSlider

class ImcCalculatorFragment : Fragment() {
    //engádense as variables de estado que gardan os valores que o usuario selecciona
    private var currentWeight: Int = 60
    private var currentAge: Int = 30
    private var currentHeight: Int = 100
    private var isFemaleSelected: Boolean = true
    //logo están as vistas que representan os elementos da interface
    private lateinit var viewFemale: CardView
    private lateinit var viewMale: CardView
    private lateinit var tvHeight: TextView
    private lateinit var rsHeight: RangeSlider
    private lateinit var tvAge: TextView
    private lateinit var tvWeight: TextView
    private lateinit var btnPlusWeight: FloatingActionButton
    private lateinit var btnSubstractWeight: FloatingActionButton
    private lateinit var btnPlusAge: FloatingActionButton
    private lateinit var btnSubstractAge: FloatingActionButton
    private lateinit var btnCalculate: Button

    //estas referencias inicializanse en onViewCreated usando findViewById

    //aquí inflase o layout do fragment
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_imc_calculator, container, false)
    }

    // inicialízanse todas as vistas e configúranse listeners e a interface inicial
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //Inicialización de vistas
        viewFemale = view.findViewById(R.id.viewFemale)
        viewMale = view.findViewById(R.id.viewMale)
        tvHeight = view.findViewById(R.id.tvHeight)
        rsHeight = view.findViewById(R.id.rsHeight)
        tvWeight = view.findViewById(R.id.tvWeight)
        btnPlusWeight = view.findViewById(R.id.btnPlusWeight)
        btnPlusAge = view.findViewById(R.id.btnPlusAge)
        btnSubstractWeight = view.findViewById(R.id.btnSubtractWeight)
        btnSubstractAge = view.findViewById(R.id.btnSubtractAge)
        tvAge = view.findViewById(R.id.tvAge)
        btnCalculate = view.findViewById(R.id.btnCalculate)
        //chámase a dúas funcións
        initListeners() //para manexas eventos de clic
        initUI() //para configurar a UI inicial cos valores por defecto
    }

    private fun initListeners() {
        //aquí defínese o que pasa cando o usuario interactúa coa UI
        //actualiza as cores das cardview
        viewMale.setOnClickListener {
            isFemaleSelected = false
            setGenderColor()
        }
        viewFemale.setOnClickListener {
            isFemaleSelected = true
            setGenderColor()
        }
        //cambia currentHeight e actualízase textView
        rsHeight.addOnChangeListener { _, value, _ ->
            currentHeight = value.toInt()
            tvHeight.text = "$currentHeight cm"
        }

        //botóns que incrementan ou decrementan os valores e actualizan os textViews
        btnPlusWeight.setOnClickListener {
            currentWeight=currentWeight + 1
            setWeight()
        }
        btnSubstractWeight.setOnClickListener {
            if (currentWeight> 1) {
                currentWeight -= 1
                setWeight()
            }
        }
        btnSubstractAge.setOnClickListener {
                if (currentAge > 1) {
                    currentAge -= 1
                    setAge()
                }
        }
        btnPlusAge.setOnClickListener {
            currentAge += 1
            setAge()
        }
        //calcula o IMC e logo navega ata o fragmento co resultado
        btnCalculate.setOnClickListener {
            val imcResult = calculateIMC()
            navigateToResult(imcResult)
        }
    }

    //resalta a tarxeta do xénero seleccionado usando cores distintas. Chámase cada vez que se cambia a selección de xénero
    private fun setGenderColor() {
        val selectedColor = ContextCompat.getColor(requireContext(), R.color.card_selected)
        val unselectedColor = ContextCompat.getColor(requireContext(), R.color.card_unselected)

        if (isFemaleSelected) {
            viewFemale.setCardBackgroundColor(selectedColor)
            viewMale.setCardBackgroundColor(unselectedColor)
        } else {
            viewFemale.setCardBackgroundColor(unselectedColor)
            viewMale.setCardBackgroundColor(selectedColor)
        }
    }

    fun setWeight() {
        tvWeight.text = currentWeight.toString()
    }
    //actualiza os textView de peso e idade cos valores actuais. Chámanse despois de cada cambio cos botóns + e -
    fun setAge() {
        tvAge.text = currentAge.toString()
    }

    fun calculateIMC(): Double {
        val imc = currentWeight / ((currentHeight.toDouble() / 100) * (currentHeight.toDouble() / 100))
        return String.format("%.2f", imc).toDouble()
        //devolve un double con dous decimais
    }

    fun initUI() { //inicializa a UI cos valores por defecto ao cargar o fragmento
        setGenderColor()
        setWeight()
        setAge()
        tvHeight.text = "$currentHeight cm" // Asegurar que a altura inicial se mostre
    }

    private fun navigateToResult(result: Double) {
        val action = ImcCalculatorFragmentDirections
            .actionImcCalculatorFragmentToResultIMCFragment(result.toFloat())
        findNavController().navigate(action)
        //navégase ao fragmento de resultados. Converte o double a float porque o argumento definido no nav graph é de tipo float
    }
}