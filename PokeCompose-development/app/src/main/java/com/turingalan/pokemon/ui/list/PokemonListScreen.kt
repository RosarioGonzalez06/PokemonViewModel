package com.turingalan.pokemon.ui.list

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.turingalan.pokemon.data.model.Pokemon
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.painterResource


@Composable
fun PokemonListItem(
    modifier: Modifier = Modifier,
    name:String,
    spriteId:Int,
    onNavigateToDetail: (Long) -> Unit,
    pokemonId: Long
) {
    Card(
        modifier = modifier.fillMaxWidth().clickable(
            enabled = true,
            onClick = { onNavigateToDetail(pokemonId)
                })
    ) {
        Row {
            Image (painterResource(spriteId),name)
        }
        Text(text = name,
            style = MaterialTheme.typography.headlineSmall)
    }
}
@Composable
fun PokemonListScreen(modifier: Modifier, onNavigateToDetail: (Long)-> Unit, viewModel: PokemonListViewModel=hiltViewModel(),) {
    val uiState by viewModel.uiState.collectAsState()
    when(uiState){
        is ListUiState.Initial->{
            TODO()
        }
        is ListUiState.Loading-> {
            Column(modifier=modifier.fillMaxSize(),
                verticalArrangement= Arrangement.Center,
                horizontalAlignment= Alignment.CenterHorizontally){
                CircularProgressIndicator(
                    modifier = Modifier.width(64.dp),
                    color = MaterialTheme.colorScheme.secondary,
                    trackColor = MaterialTheme.colorScheme.surfaceVariant,
                )}
        }
            is ListUiState.Success -> {
                Column(modifier = modifier.fillMaxSize()) {
                    LazyColumn(modifier = modifier.fillMaxSize()) {
                        items(items=(uiState as ListUiState.Success).pokemons, key= {
                                item ->
                            item.id
                        }) {
                            PokemonListItem(
                                name = it.name,
                                spriteId = it.spriteId,
                                pokemonId = it.id,
                                onNavigateToDetail = onNavigateToDetail,

                                )
                        }


                    }
                }
            }
        }
    }
