package com.turingalan.pokemon.ui.list

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.turingalan.pokemon.data.model.Pokemon
import com.turingalan.pokemon.data.repository.PokemonRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonListViewModel @Inject constructor(private val savedStateHandle: SavedStateHandle, private val repository: PokemonRepository): ViewModel(){
    private val _uiState: MutableStateFlow<ListUiState> = MutableStateFlow(ListUiState.Initial)
    val uiState: StateFlow<ListUiState>
        get()= _uiState.asStateFlow()
    init {
        viewModelScope.launch {
            _uiState.value= ListUiState.Loading
            val allPokemon=repository.readAll()
            val successResponse = ListUiState.Success(allPokemon.asListUiState())
            _uiState.value=successResponse
        }

    }
}

fun Pokemon.asListItemUiState(): ListItemUiState{
    return ListItemUiState(
        id=this.id,
        name=this.name,
        spriteId=this.spriteId
    )
}

fun List<Pokemon>.asListUiState(): List<ListItemUiState> = this.map(Pokemon::asListItemUiState)


data class ListItemUiState(
    val id: Long,
    val name: String,
    val spriteId: Int,
)
sealed class ListUiState {
    object Initial : ListUiState()
    object Loading : ListUiState()
    data class Success(val pokemons: List<ListItemUiState>) : ListUiState()
}
