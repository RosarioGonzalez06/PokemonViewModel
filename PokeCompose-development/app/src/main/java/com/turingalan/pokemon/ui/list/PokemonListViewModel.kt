package com.turingalan.pokemon.ui.list

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.turingalan.pokemon.data.repository.PokemonRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PokemonListViewModel @Inject constructor(private val savedStateHandle: SavedStateHandle, private val repository: PokemonRepository): ViewModel(){

}