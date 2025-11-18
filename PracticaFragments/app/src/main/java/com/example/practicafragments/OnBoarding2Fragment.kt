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

class OnBoarding2Fragment : Fragment(R.layout.fragment_on_boarding2){

    private lateinit var navController: NavController
    var botonFinalizar: Button? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = findNavController()
        botonFinalizar = view.findViewById(R.id.botonFinalizar)

        botonFinalizar?.setOnClickListener {
            navController.navigate(R.id.action_onBoarding2Fragment_to_homeFragment)
        }
    }
}
