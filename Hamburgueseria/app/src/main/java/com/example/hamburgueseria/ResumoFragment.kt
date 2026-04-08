package com.example.hamburgueseria

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.hamburgueseria.databinding.FragmentResumoBinding

class ResumoFragment : Fragment() {

    //Variable privada nullable que almacena o binding. Úsase para acceder aos elementos do XML
    private var _binding: FragmentResumoBinding? = null
    //Variable pública binding. Permite acceder aos elementos e !! indica que non é nula
    private val binding: FragmentResumoBinding
        get() = _binding!!

    //Variable model do tipo OrderViewModel. by viewModels crea o Viewmodel e ownerProducer indica que é compartido coa actividade
    val model: OrderViewModel by viewModels(
        ownerProducer = { this.requireActivity() }
    )
    //Métod que se executa cando se crea a vista do fragment
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Infla o layout usando View Binding
        _binding = FragmentResumoBinding.inflate(inflater, container, false)
        //Garda a vista raíz do layout
        val view = binding.root
        //Devolve a vista raíz
        return view
    }
    //Métod que se executa despois de crear a vista
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //Obtén os argumentos enviados dende PedidoFragment
        val args = ResumoFragmentArgs.fromBundle(requireArguments())
        //Mostra o nome do usuario na pantalla
        binding.textName.text = args.userName
        //Observa o total do pedido no ViewModel
        model.total.observe(viewLifecycleOwner){
            //Mostra o total na pantalla
            binding.textTotal.text = "Total: $it €"
        }
        //Evento click do botón flotante (fab)
        binding.fab.setOnClickListener {
            //Borra o total do pedido no ViewModel
            model.clear()
        }
    }
    //Métod que se executa cando se destrúe a vista
    override fun onDestroyView() {
        super.onDestroyView()
        //_binding ponse a null para liberar memoria e evitar erros
        _binding = null
    }
}