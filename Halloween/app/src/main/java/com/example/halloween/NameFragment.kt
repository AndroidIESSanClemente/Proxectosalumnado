package com.example.halloween

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.navigation.fragment.findNavController

class NameFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_name, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val editTextName = view.findViewById<EditText>(R.id.edit_text_name)
        val buttonContinue = view.findViewById<Button>(R.id.button_continue)

        buttonContinue.setOnClickListener {
            val name = editTextName.text.toString().trim()
            val action = NameFragmentDirections.actionNameFragmentToThemeFragment(name)
            findNavController().navigate(action)
        }
    }
}

// No onCreateView inflase o layout asociado ao fragmento para que se mostre na pantalla. inflater.inflate converte o XML nun obxeto View que se pode mostrar
// En onViewCreated chámase despois de que se cree a vista, e é onde se configura a lóxica da UI (interface de usuario), como botóns, campos de texto...
// Nel decláranse as variables e buscanse no layout polo seu ID
// Finalmente configurase a acción do botón creando unha acción de navegación pasando o argumento ao seguinte fragmento