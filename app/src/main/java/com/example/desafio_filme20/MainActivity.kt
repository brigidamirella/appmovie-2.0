package com.example.desafio_filme20

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.desafio_filme20.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    /*
    TODO - 1
     Aqui no top level destinations a tela de detalhes como ela é destino das suas telas que é a lista não tem a necessidade de ser adicionada,
     até pra tela de detalhe mostrar para o usuário o botão de back na AppBar
     */
    private val appBarConfiguration = AppBarConfiguration(
        setOf(
            R.id.nav_movie, R.id.nav_favorites, R.id.nav_details
        )
    )
    private lateinit var navController: NavController


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        navController = findNavController(R.id.nav_host_fragment)

        setupActionBarWithNavController(navController, appBarConfiguration)
        binding.apply {
            navView.setupWithNavController(navController)
        }
    }

    /*
    TODO - 2
        Adicionar a funcão onSupportNavigateUp com o navController navigate up pra da suporte ao botão back da tela de destinos
        */
    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

}