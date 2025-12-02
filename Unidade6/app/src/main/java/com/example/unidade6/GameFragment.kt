package com.example.unidade6

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import com.example.unidade6.databinding.FragmentGameBinding
import com.google.android.material.snackbar.Snackbar

class GameFragment : Fragment() {
    private var _binding: FragmentGameBinding? = null
    private val binding get() = _binding!!

    val model: GameViewModel by viewModels(
        ownerProducer = { this.requireActivity() }
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentGameBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Iniciamos a palabra
        // updateScreen()
        val wordObserver = Observer<String> { newWord ->
            binding.txtWord.text = newWord  //Actualizamos o TextView
        }
        //Asóciase o LiveData co observador
        model.secretWordDisplay.observe(viewLifecycleOwner, wordObserver)
        //Faise o mesmo para as vidas, pero con INT
        val livesObserver = Observer<Int> { newLives ->
            binding.txtLives.text = "Quédanche $newLives vidas"
        }
        model.lives.observe(viewLifecycleOwner, livesObserver)

        //Observador para limpar o campo de texto
        model.clearInput.observe(viewLifecycleOwner){
                binding.txtGuess.text.clear()
        }

        binding.buttonNext.setOnClickListener {
            if (binding.txtGuess.text.length>0){
            //comprobar a letra introducida
                model.makeGuess(binding.txtGuess.text.toString())
            //Actualizamos a pantalla no novo estado
          //  updateScreen()
            //Se acertamos a palabra ou nos quedamos sen vidas, navegamos
            if (model.win() || model.lost())
                view.findNavController().navigate(R.id.action_gameFragment_to_resultFragment)
            } else {
                //Senón se introduce texto móstrase un aviso
                Snackbar.make(view, "Introduce unha letra", Snackbar.LENGTH_SHORT).show()
            }
        }
    }

    //Cada vez que se cambiaba algo chamábase manualmente con updateScreen(), agora con LiveData o texto actualízase automaticamente
    //  fun updateScreen(){
    //    binding.txtWord.text = model.secretWordDisplay
    //    binding.txtLives.text = "Quédanche ${model.lives} vidas"
     //   binding.txtGuess.text= null
    //}

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}