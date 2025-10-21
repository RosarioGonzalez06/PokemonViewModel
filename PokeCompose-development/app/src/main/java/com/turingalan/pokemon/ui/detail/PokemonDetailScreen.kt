package com.turingalan.pokemon.ui.detail

import android.R.attr.text
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.turingalan.pokemon.R

@Composable
fun PokemonDetailScreen(
    modifier: Modifier = Modifier,
    viewModel: PokemonDetailViewModel= hiltViewModel(),
    onCancel: ()-> Unit
){
    val uiState by viewModel.uiState.collectAsState()
    Column(modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally) {
        Image(
            painterResource(uiState.artworkId),
            contentDescription = uiState.name,
            contentScale = ContentScale.Crop,

            )
        Button(
            onClick = onCancel
        ) {
            Text("return")
        }
    }
}
