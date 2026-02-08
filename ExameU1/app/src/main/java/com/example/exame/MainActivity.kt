package com.example.exame

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.NavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.exame.databinding.ActivityMainBinding
import com.google.android.material.appbar.MaterialToolbar

class MainActivity : AppCompatActivity() {

    //Variable para almacenar a referencia do View Binding
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        // Inflar o deseño utilizando View Binding
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Contedor onde se van cargando os fragments
        // O NavController non existe só, Android créao dentro do NavHostFragment. O NavController non está na activity nin no layout directamente, vive dentro do NavHostFragment.
        // Por exemplo, a toolbar está no layout e cóllese directamente con findViewById, pero o NavController é un caso especial porque non se pode coller directamente.
        // Primeiro hai que obter o NavHostFragment, que é o contedor dos fragments, e despois sacar de aí o NavController. Por iso hai ese paso extra e por iso é necesario usar o NavHostFragment.
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController

        // Indícase que a toolbar vai ser a ActionBar, o que permite que apareza a hamburguesa e a frecha e se sincronice coa navegación
        val toolbar = findViewById<MaterialToolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        // AppBarConfiguration con DrawerLayout
        appBarConfiguration = AppBarConfiguration.Builder(navController.graph)
            .setOpenableLayout(binding.drawerLayout)
            .build()

        // Conecta ActionBar (hamburguesa / atrás) con NavController
        setupActionBarWithNavController(navController, appBarConfiguration)

        // Conéctase NavigationView (menú lateral) con NavController, é dicir fai que cando o usuario toca unha opción do menú se nevegue automaticamente ao fragment correspondente
        binding.navView.setupWithNavController(navController)
    }

    //Permite que a frecha de ir cara atrás funcione
    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(navController, appBarConfiguration)
    }
}