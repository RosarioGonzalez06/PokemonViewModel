package com.turingalan.pokemon.ui.detail

import androidx.compose.runtime.MutableState
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.navigation.toRoute
import com.turingalan.pokemon.data.model.Pokemon
import com.turingalan.pokemon.data.repository.PokemonRepository
import com.turingalan.pokemon.destinations
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject


@HiltViewModel
class PokemonDetailViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val pokemonRepository: PokemonRepository

): ViewModel() {
    private val _uiState: MutableStateFlow<DetailUiState> =
            MutableStateFlow(DetailUiState())
    val uiState: StateFlow<DetailUiState>
        get() =_uiState.asStateFlow()
    init {
        val route= savedStateHandle.toRoute<destinations.Detail>()
        val pokemonId=route.id
            val pokemon =pokemonRepository.readOne(pokemonId)
        pokemon?.let{
            _uiState.value = pokemon.toDetailUiState()
        }

    }
    fun Pokemon.toDetailUiState(): DetailUiState = DetailUiState(
        name = this.name,
        artworkId=this.artworkId,
    )
}
data class DetailUiState(
    val name:String="",
    val artworkId:Int= 0,
)