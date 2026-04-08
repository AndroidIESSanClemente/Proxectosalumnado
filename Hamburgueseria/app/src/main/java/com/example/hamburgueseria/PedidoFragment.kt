package com.example.hamburgueseria

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.example.hamburgueseria.databinding.FragmentPedidoBinding
import com.google.android.material.snackbar.Snackbar

class PedidoFragment : Fragment() {

    //Variable privada nullable que almacena o binding. Úsase para acceder aos elementos do XML
    private var _binding: FragmentPedidoBinding? = null
    //Variable pública binding. Permite acceder aos elementos e !! indica que non é nulo
    private val binding: FragmentPedidoBinding
        get() = _binding!!
    //Variable model do tipo OrderViewModel. by viewModels crea o ViewModel e ownerProducer indica que é compartido coa actividade
    val model: OrderViewModel by viewModels(
        ownerProducer = { this.requireActivity() }
    )
    //Métod que se executa cando se crea a vista do Fragment
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Infla o layout usando View Binding
        _binding = FragmentPedidoBinding.inflate(inflater, container, false)
        //Devolve a vista raíz do layout
        return binding.root
    }
    //Métod que se executa despois de crear a vista
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //Obsérvase o nome no PedidoFragment
        model.name.observe(viewLifecycleOwner) {
            binding.userName.setText(it)
        }
        //Evento click do botón engadir pedido
        binding.buttonadd.setOnClickListener {
            //Obtén o nome introducido polo usuario e convérteo a String
            val name = binding.userName.text.toString()
            //Variable que garda o total do pedido
            var total = 0.0
            //When que comproba que hamburguesa foi seleccionada, e suma unha cantidade en función diso
            when (binding.chipGroupBurger.checkedChipId) {
                R.id.chip_completa -> total += 6.5
                R.id.chip_vegana -> total += 7
                R.id.chip_conovo -> total += 7.5
            }
            //When que comproba que bebida foi seleccionada
            when (binding.chipGroupDrink.checkedChipId) {
                R.id.chip_auga -> total += 1.5
                R.id.chip_refresco -> total += 2
            }
            //Engade o total do pedido ao ViewModel para gardalo e poder compartilo con outros fragments
            model.addOrder(total, name)
            //Mostra unha mensaxe de curta duración na pantalla
            Snackbar.make(binding.root, "Pedido engadido", Snackbar.LENGTH_SHORT).show()
            //Navega ao ResumoFragment e envía o nome do usuario
            view.findNavController().navigate(

                PedidoFragmentDirections
                    .actionPedidoFragmentToResumoFragment(name)
            )
        }
    }
    //Métod que se executa cando se destrúa a vista
    override fun onDestroyView() {
        super.onDestroyView()
        // _binding ponse a null para liberar memoria e evitar erros
        _binding = null
    }
}