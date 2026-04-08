package com.example.hamburgueseria

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.hamburgueseria.databinding.ActivityMainBinding
import androidx.navigation.ui.setupActionBarWithNavController

//Declárase a clase
class MainActivity : AppCompatActivity() {

    // Variable para almacenar a referencia do View Binding e acceder aos elementos do XML
    private lateinit var binding: ActivityMainBinding
    //Variable que controla a navegación entre pantallas
    private lateinit var navController: NavController
    //Métod que se executa cando se crea a actividade
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // Inflar o deseño utilizando View Binding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root) //Mostra o layout na pantalla
        //Axusta os espazos das barras
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        //Configurase a Toolbar como a barra principal da app
        setSupportActionBar(binding.toolbar)
        //Busca o fragment que contén a navegación
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragment_container_view)
                    as NavHostFragment
        //Obtén o controlador da navegación
        navController = navHostFragment.navController
        //Permite que o botón atrás funcione correctamente. E fai que apareza a frecha
        setupActionBarWithNavController(navController)
    }
    //Execútase cando se preme o botón atrás da toolbar
    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() //volve á pantalla anterior. Fai que a frecha funcione
    }
}