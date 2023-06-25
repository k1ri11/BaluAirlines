package com.company.baluairlines.buy_ticket_feature.presentation.adapters

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import com.company.baluairlines.buy_ticket_feature.presentation.viewmodels.SearchResultViewModel
import com.company.baluairlines.core.domain.CostItem
import com.company.myapplication.R
import com.company.myapplication.databinding.ItemFlightCostBinding

/**
 * класс viewholder для каждого элемента ресайклера
 */
class CostViewHolder(
    private val binding: ItemFlightCostBinding,
    private val viewModel: SearchResultViewModel,
    private val context: Context
) : RecyclerView.ViewHolder(binding.root) {
    /**
     * функция заполнения элемента ресайклера
     * @param currentItem текущий элемент
     */
    fun bind(currentItem: CostItem) {
        binding.apply {
            binding.root.setOnClickListener { viewModel.getFlights(currentItem.fullDate) }
            flightDate.text = currentItem.date
            if (currentItem.cost == 0) flightCost.text = context.getString(R.string.costs_not_found)
            else flightCost.text = context.getString(R.string.flight_cost, currentItem.cost)
            passengers.text = context.getString(R.string.passengers_cost, currentItem.passengers)
        }
    }

}