package com.example.pokedexwithcompose.repository

import com.example.pokedexwithcompose.data.remote.PokeApi
import com.example.pokedexwithcompose.util.Resource
import com.plcoding.jetpackcomposepokedex.data.remote.responses.Pokemon
import com.plcoding.jetpackcomposepokedex.data.remote.responses.PokemonList
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

@ActivityScoped
class PokemonRepository @Inject constructor(
    private val api: PokeApi
) {
    suspend fun getPokemonList(limit: Int, offset: Int): Resource<PokemonList> {
        val response = try {
            api.getPokemonList(limit, offset)
        } catch (e: Exception) {
            println("PokemonRepository.getPokemonList" + e.message)
            return Resource.Error("Um erro desconhecido ocorreu.")
        }
        return Resource.Success(response)
    }

    suspend fun getPokemonInfo(pokemonName: String): Resource<Pokemon> {
        val response = try {
            api.getPokemonInfo(pokemonName)
        } catch (e: Exception) {
            return Resource.Error("Um erro desconhecido ocorreu.")
        }
        return Resource.Success(response)
    }
}

