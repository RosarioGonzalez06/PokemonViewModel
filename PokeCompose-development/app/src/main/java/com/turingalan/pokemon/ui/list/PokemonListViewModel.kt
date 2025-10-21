package com.turingalan.pokemon.ui.list

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.turingalan.pokemon.data.model.Pokemon
import com.turingalan.pokemon.data.repository.PokemonRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class PokemonListViewModel @Inject constructor(private val savedStateHandle: SavedStateHandle, private val repository: PokemonRepository): ViewModel(){
    private val _uiState: MutableStateFlow<ListUiState> = MutableStateFlow(ListUiState())
    val uiState: StateFlow<ListUiState>
        get()= _uiState.asStateFlow()
    init {
        val allPokemon=repository.readAll()
        val listUiState=allPokemon.asListUiState()
        _uiState.value=listUiState
    }
}

fun Pokemon.asListItemUiState(): ListItemUiState{
    return ListItemUiState(
        id=this.id,
        name=this.name,
        spriteId=this.spriteId
    )
}

fun List<Pokemon>.asListUiState(): ListUiState{
    return ListUiState(this.map(Pokemon::asListItemUiState))
}

data class ListItemUiState(
    val id: Long,
    val name: String,
    val spriteId: Int,
)
data class ListUiState(
    val list: List <ListItemUiState> = listOf()
)