package com.turingalan.pokemon.ui

import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.R
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.turingalan.pokemon.data.model.Pokemon
import com.turingalan.pokemon.destinations
import com.turingalan.pokemon.ui.detail.PokemonDetailScreen
import com.turingalan.pokemon.ui.list.PokemonListScreen
import com.turingalan.pokemon.ui.list.PokemonListViewModel

@OptIn(ExperimentalMaterial3Api::class)

@Composable
fun PokemonApp() {
    val viewModel: PokemonListViewModel = hiltViewModel()
    val navController = rememberNavController()
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text="Pokemon App",
                        style = MaterialTheme.typography.headlineLarge,
                        fontWeight = FontWeight.ExtraBold
                    )
                }
            )
        },
    ) { innerPadding ->
        NavHost(navController= navController, startDestination = destinations.List){
            val hostModifier= Modifier.consumeWindowInsets(innerPadding).padding(innerPadding)
            composable <destinations.List>{
                PokemonListScreen(
                    modifier = hostModifier,
                    onNavigateToDetail = {
                            id -> navController.navigate(destinations.Detail(id))
                    },
                )
            }
            composable <destinations.Detail>{
                PokemonDetailScreen(
                    modifier = hostModifier,
                    onCancel = {navController.popBackStack()},

                )
            }
        }
    }
}