package com.company.baluairlines.buy_ticket_feature.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.company.baluairlines.buy_ticket_feature.di.SearchResultFragmentScope
import com.company.baluairlines.core.domain.Flight
import com.company.myapplication.databinding.ItemFlightBinding
import javax.inject.Inject

/**
 * класс адаптера для recyclerView с полетами
 * @property flightList содержит элементы для ресайклера
 */
@SearchResultFragmentScope
class FlightListAdapter @Inject constructor(): RecyclerView.Adapter<FlightListAdapter.FlightViewHolder>()  {
    var flightList: List<Flight> = emptyList()
        set(newValue) {
            val diffCallback = FlightListDiffUtilCallback(field, newValue)
            val diffResult = DiffUtil.calculateDiff(diffCallback)
            field = newValue
            diffResult.dispatchUpdatesTo(this)
        }

    /**
     * класс viewholder для каждого элемента ресайклера
     * @param binding xml разметка для элемента ресайклера
     */
    inner class FlightViewHolder(private val binding: ItemFlightBinding) :
        RecyclerView.ViewHolder(binding.root) {

        /**
         * функция заполнения элемента ресайклера
         * @param currentItem текущий элемент
         */
        fun bind(currentItem: Flight) {
            binding.apply {
                departureTime.text = currentItem.flights.first().scheduledDepartureTime
                arrivalTime.text = currentItem.flights.last().scheduledArrivalTime
                //todo проверить как приходит время
                flightTime.text = currentItem.time
                flightCost.text = currentItem.cost.toString()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FlightListAdapter.FlightViewHolder {
        val binding = ItemFlightBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FlightViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FlightListAdapter.FlightViewHolder, position: Int) {
        val currentItem = flightList[position]
        holder.bind(currentItem)
    }

    /**
     * функция возврата количества элементов в листе
     * @return количество элементов в листе
     */
    override fun getItemCount(): Int {
        return flightList.size
    }
}