package com.turingalan.pokemon.ui.list

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun PokemonListScreen(modifier: Modifier, onNavigateToDetail: (Int)-> Unit, viewModel: PokemonListViewModel=hiltViewModel()) {
    Column(modifier = modifier.fillMaxSize()) {
        LazyColumn(modifier = modifier.fillMaxSize()) {
            items(pokemonList) { champion ->
                ChampionListItem(
                    onClick = onNavigateToDetail

                )

            }


        }
    }
}