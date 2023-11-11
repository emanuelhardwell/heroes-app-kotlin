package com.emanuel.mysecondapp.superhero

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.emanuel.mysecondapp.databinding.ItemSuperheroBinding
import com.squareup.picasso.Picasso

class SuperHeroViewHolder (view: View): RecyclerView.ViewHolder(view) {

    private var binding= ItemSuperheroBinding.bind(view)

     fun bind(superHero: SuperHeroItem, onSelectedItem: (String) -> Unit) {
         binding.textViewSuperHeroName.text= superHero.name
         //binding.imageViewSuperHero
         Picasso.get().load(superHero.image.url).into(binding.imageViewSuperHero);

         binding.cardViewSuperHero.setOnClickListener {
             onSelectedItem(superHero.id)
         }
    }
}