package com.turingalan.pokemon.ui.list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.turingalan.pokemon.data.model.Pokemon
import androidx.compose.foundation.lazy.items


@Composable
fun PokemonListItem(
    pokemon: Pokemon,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(16.dp)
    ) {
        Text(text = pokemon.name)
    }
}
@Composable
fun PokemonListScreen(modifier: Modifier, onNavigateToDetail: (Int)-> Unit, viewModel: PokemonListViewModel=hiltViewModel()) {
    val pokemonList = viewModel.pokemonList
    Column(modifier = modifier.fillMaxSize()) {
        LazyColumn(modifier = modifier.fillMaxSize()) {
            items(pokemonList) { pokemon ->
                PokemonListItem(
                    pokemon = pokemon,
                    onClick = { onNavigateToDetail(pokemon.id.toInt()) }
                )

            }


        }
    }
}