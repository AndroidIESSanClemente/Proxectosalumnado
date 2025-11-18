package com.example.halloween

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs

class ThemeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_theme, container, false)
    }

    private val args: ThemeFragmentArgs by navArgs()
    //recolle os argumentos pasados dende o fragmento anterior (NameFragment)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val cardCasa = view.findViewById<CardView>(R.id.card_casa)
        val cardBosque = view.findViewById<CardView>(R.id.card_bosque)
        val cardBruxas = view.findViewById<CardView>(R.id.card_bruxas)

        // configuranse os clics. Cada tarxeta ten un listener que chama a navigateToStory() co tema correspondente cando o usuario fai clic
        cardCasa.setOnClickListener {
            navigateToStory("Casa encantada")
        }
        cardBosque.setOnClickListener {
            navigateToStory("Bosque maldito")
        }
        cardBruxas.setOnClickListener {
            navigateToStory("Aquelarre de bruxas")
        }
    }
// por último créase unha acción de navegación usando Safe Args, pasando args.userName, o nome de usuario que veu do fragmento anterior e o tema seleccionado polo usuario.
    private fun navigateToStory(theme: String) {
        val action = ThemeFragmentDirections.actionThemeFragmentToStoryFragment(args.userName, theme)
        findNavController().navigate(action)
    }
}

