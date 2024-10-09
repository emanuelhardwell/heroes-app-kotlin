package com.emanuel.mysecondapp.superhero

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.emanuel.mysecondapp.R

class SuperHeroAdapter(var superHeroItemList: List<SuperHeroItem> = emptyList(), var onSelectedItem: (String) -> Unit ) :
    RecyclerView.Adapter<SuperHeroViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SuperHeroViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return SuperHeroViewHolder(layoutInflater.inflate(R.layout.item_superhero, parent, false))
    }

    override fun onBindViewHolder(viewHolder: SuperHeroViewHolder, position: Int) {
        val superHero = superHeroItemList[position]
        return viewHolder.bind(superHero, onSelectedItem)
    }

    override fun getItemCount(): Int {
        return superHeroItemList.size
    }

    fun updateSuperHeroItemList(superHeroItemList: List<SuperHeroItem>) {
        this.superHeroItemList = superHeroItemList
        notifyDataSetChanged()
    }
}