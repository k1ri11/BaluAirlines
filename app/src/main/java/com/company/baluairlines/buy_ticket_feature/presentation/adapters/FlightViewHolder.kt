package com.company.baluairlines.buy_ticket_feature.presentation.adapters

import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment.Companion.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.company.baluairlines.buy_ticket_feature.presentation.fragments.SearchResultFragmentDirections
import com.company.baluairlines.core.domain.Flight
import com.company.myapplication.databinding.ItemFlightBinding

/**
 * класс viewholder для каждого элемента ресайклера
 * @param binding xml разметка для элемента ресайклера
 */
class FlightViewHolder(
    private val binding: ItemFlightBinding,
    private val fragment: Fragment
    ) : RecyclerView.ViewHolder(binding.root) {

    /**
     * функция заполнения элемента ресайклера
     * @param currentItem текущий элемент
     */
    fun bind(currentItem: Flight) {
        binding.apply {
            root.setOnClickListener {
                val action = SearchResultFragmentDirections.actionSearchResultFragmentToFlightDetailsFragment(currentItem)
                findNavController(fragment).navigate(action)
            }
            departureTime.text = currentItem.flights.first().scheduledDepartureTime
            arrivalTime.text = currentItem.flights.last().scheduledArrivalTime
            //todo проверить как приходит время
            flightTime.text = currentItem.time
            flightCost.text = currentItem.cost.toString()
        }
    }
}