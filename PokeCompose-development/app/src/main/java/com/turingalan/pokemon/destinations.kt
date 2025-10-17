package com.turingalan.pokemon

import kotlinx.serialization.Serializable

@Serializable
sealed class destinations (val route: String) {
    @Serializable
    object List: destinations("PokemonListScreen")
    @Serializable
    data class Detail (val id:Int): destinations("detailScreen/$id")
}