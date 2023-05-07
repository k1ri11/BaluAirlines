package com.company.baluairlines.buy_ticket_feature.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
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
class FlightListAdapter @Inject constructor(
    private val fragment: Fragment
): RecyclerView.Adapter<FlightViewHolder>()  {
    var flightList: List<Flight> = emptyList()
        set(newValue) {
            val diffCallback = FlightListDiffUtilCallback(field, newValue)
            val diffResult = DiffUtil.calculateDiff(diffCallback)
            field = newValue
            diffResult.dispatchUpdatesTo(this)
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FlightViewHolder {
        val binding = ItemFlightBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FlightViewHolder(binding, fragment)
    }

    override fun onBindViewHolder(holder: FlightViewHolder, position: Int) {
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