package com.example.pokeman.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.chip.Chip
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade
import com.example.pokeman.data.Pokemon
import com.example.pokeman.PokemonDetailActivity
import com.example.pokeman.R
import com.example.pokeman.utilities.EXTRA_POKEMON
import com.example.pokeman.utilities.isDualType

class PokemonAdapter(val context: Context, val pokemons: List<Pokemon>) :
    RecyclerView.Adapter<PokemonAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_pokemon, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val pokemon = pokemons[position]
        holder.bind(pokemon)
    }

    override fun getItemCount() = pokemons.size

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val cardView = itemView.findViewById<CardView>(R.id.cardView)
        private val tvNumber = itemView.findViewById<TextView>(R.id.tvNumber)
        private val tvName = itemView.findViewById<TextView>(R.id.tvName)
        private val ivSprite = itemView.findViewById<ImageView>(R.id.ivSprite)
        private val chipType1 = itemView.findViewById<Chip>(R.id.chipType1)
        private val chipType2 = itemView.findViewById<Chip>(R.id.chipType2)

        fun bind(pokemon: Pokemon) {
            tvNumber.text = "#${pokemon.id.toString().padStart(3, '0')}"
            tvName.text = pokemon.name
            // Might remove transition
            Glide.with(context).load(pokemon.sprite).transition(withCrossFade()).into(ivSprite)
            chipType1.text = pokemon.type1
            if (isDualType(pokemon)) {
                chipType2.text = pokemon.type2
                chipType2.visibility = View.VISIBLE
            } else {
                chipType2.visibility = View.GONE
            }
            // Navigate to details page
            cardView.setOnClickListener {
                val intent = Intent(context, PokemonDetailActivity::class.java)
                intent.putExtra(EXTRA_POKEMON, pokemon)
                context.startActivity(intent)
            }
            setupColors(pokemon)
        }

        private fun setupColors(pokemon: Pokemon) {
            val textColor1 = getTextColor(pokemon.type1)
            chipType1.setTextAppearanceResource(textColor1)
            chipType1.setChipStrokeColorResource(getStrokeColor(pokemon.type1))
            if (isDualType(pokemon)) {
                val textColor2 = getTextColor(pokemon.type2)
                chipType2.setTextAppearance(textColor2)
                chipType2.setChipStrokeColorResource(getStrokeColor(pokemon.type2))
            }
        }
    }

    // Color Utility
    private fun getTextColor(type: String): Int {
        return when (type) {
            "normal" -> R.style.normal
            "fire" -> R.style.fire
            "water" -> R.style.water
            "grass" -> R.style.grass
            "electric" -> R.style.electric
            "ice" -> R.style.ice
            "fighting" -> R.style.fighting
            "poison" -> R.style.poison
            "ground" -> R.style.ground
            "flying" -> R.style.flying
            "psychic" -> R.style.psychic
            "bug" -> R.style.bug
            "rock" -> R.style.rock
            "ghost" -> R.style.ghost
            "dark" -> R.style.dark
            "dragon" -> R.style.dragon
            "steel" -> R.style.steel
            "fairy" -> R.style.fairy
            else -> R.style.grass
        }
    }

    private fun getStrokeColor(type: String): Int {
        return when (type) {
            "normal" -> R.color.normal
            "fire" -> R.color.fire
            "water" -> R.color.water
            "grass" -> R.color.grass
            "electric" -> R.color.electric
            "ice" -> R.color.ice
            "fighting" -> R.color.fighting
            "poison" -> R.color.poison
            "ground" -> R.color.ground
            "flying" -> R.color.flying
            "psychic" -> R.color.psychic
            "bug" -> R.color.bug
            "rock" -> R.color.rock
            "ghost" -> R.color.ghost
            "dark" -> R.color.dark
            "dragon" -> R.color.dragon
            "steel" -> R.color.steel
            "fairy" -> R.color.fairy
            else -> R.color.black
        }
    }

}