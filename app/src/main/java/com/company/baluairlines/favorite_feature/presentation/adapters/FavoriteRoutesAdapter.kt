package com.company.baluairlines.favorite_feature.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.company.baluairlines.core.data.database.Route
import com.company.myapplication.databinding.ItemRoutesBinding

class FavoriteRoutesAdapter: RecyclerView.Adapter<FavoriteRoutesAdapter.FavoriteRoutesViewHolder>() {

    var routes: List<Route> = emptyList()

    inner class FavoriteRoutesViewHolder(
        private val binding: ItemRoutesBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(currentItem: Route) {
            binding.departureAirport.text = currentItem.departureAirport
            binding.arrivalAirport.text = currentItem.arrivalAirport
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteRoutesViewHolder {
        val binding =
            ItemRoutesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FavoriteRoutesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FavoriteRoutesViewHolder, position: Int) {
        val currentItem = routes[position]
        holder.bind(currentItem)
    }

    override fun getItemCount(): Int = routes.size
}