package com.example.practicafragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.annotation.NonNull
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController

//inflase o Layout
class OnBoarding1Fragment : Fragment(R.layout.fragment_on_boarding1) {

    private lateinit var navController: NavController
    var botonSeguinte: Button? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = findNavController()
        //inicialización do botón
        botonSeguinte = view.findViewById(R.id.botonSeguinte)
//Clic listener. Úsase o oeprado de chamada segura '?'
        botonSeguinte?.setOnClickListener {
            navController.navigate(R.id.action_onBoarding1Fragment_to_onBoarding2Fragment)
        }
    }
}
