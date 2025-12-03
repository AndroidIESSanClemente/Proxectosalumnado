package com.example.exame

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.navigation.findNavController

class PerfilFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_perfil, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val nameEditText = view.findViewById<EditText>(R.id.name)
        val buttonOrder = view.findViewById<Button>(R.id.button_order)

        buttonOrder.setOnClickListener {
            val name = nameEditText.text.toString()
            if (name.isEmpty()) {
                Toast.makeText(activity, "Debes completar o nome", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }
            Toast.makeText(activity, "Datos gardados", Toast.LENGTH_SHORT).show()
            val action = PerfilFragmentDirections.actionPerfilFragmentToPedidoFragment(name)
            view.findNavController().navigate(action)
        }
    }
}