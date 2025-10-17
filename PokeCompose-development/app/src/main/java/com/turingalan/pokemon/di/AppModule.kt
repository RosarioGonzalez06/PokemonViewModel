package com.turingalan.pokemon.di

import com.turingalan.pokemon.data.repository.PokemonInMemoryRepository
import com.turingalan.pokemon.data.repository.PokemonRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {

    @Binds
    @Singleton
    abstract fun bindPokemonRepository(repository: PokemonInMemoryRepository): PokemonRepository
}