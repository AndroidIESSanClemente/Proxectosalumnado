package com.example.exame

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.navigation.fragment.findNavController

class PedidoFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_pedido, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Recibir o nome do usuario dende Safe Args
        val args = PedidoFragmentArgs.fromBundle(requireArguments())
        val nomeUsuario = args.name

        // Mostrar a frase completa no TextView
        val textName = view.findViewById<TextView>(R.id.text_name)
        textName.text = "Este é o pedido de $nomeUsuario"

        // Configurar o botón para finalizar o pedido
        val endOrderButton = view.findViewById<Button>(R.id.endorder)
        endOrderButton.setOnClickListener {
            findNavController().navigate(R.id.action_pedidoFragment_to_perfilFragment)
        }
    }
}