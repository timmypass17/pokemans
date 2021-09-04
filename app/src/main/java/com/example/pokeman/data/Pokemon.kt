package com.example.pokeman.data

import android.os.Parcelable
import com.google.firebase.firestore.PropertyName
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

// Need to initalize values for firebase
@Parcelize
data class Pokemon(
    val name: String = "",
    val id: Int = 0,
    val sprite: String = "",
    val official_art: String = "",
    val type1: String = "",
    val type2: String = "",
    val stats: Map<String, Int>,
    val abilities: Map<String, String>
) : Parcelable

// not collection name
data class PokemonList(
    @PropertyName("pokemons") val pokemons: List<Pokemon>? = null
)

