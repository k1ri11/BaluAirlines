package com.company.baluairlines.services_feature.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.company.baluairlines.core.domain.FlightInfo
import com.company.baluairlines.services_feature.di.ServicesFlightFragmentScope
import com.company.myapplication.databinding.ItemServicesFlightBinding
import javax.inject.Inject


/**
 * класс адаптера для recyclerView с полетами
 * @property flightList содержит элементы для ресайклера
 */
@ServicesFlightFragmentScope
class ServicesFlightAdapter @Inject constructor() : RecyclerView.Adapter<ServicesFlightAdapter.FlightViewHolder>() {

    var flightList: List<FlightInfo> = emptyList()
        set(newValue) {
            val diffCallback = FlightDiffUtilCallback(field, newValue)
            val diffResult = DiffUtil.calculateDiff(diffCallback)
            field = newValue
            diffResult.dispatchUpdatesTo(this)
        }

    /**
     * класс viewholder для каждого элемента ресайклера
     * @param binding xml разметка для элемента ресайклера
     */
    inner class FlightViewHolder(private val binding: ItemServicesFlightBinding) :
        RecyclerView.ViewHolder(binding.root) {

        /**
         * функция заполнения элемента ресайклера
         * @param currentItem текущий элемент
         */
        fun bind(currentItem: FlightInfo) {
            binding.apply {
                departureTime.text = currentItem.scheduledDepartureTime
                arrivalTime.text = currentItem.scheduledArrivalTime
                flightStatus.text = currentItem.status
                flightNumber.text = currentItem.flightNo
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FlightViewHolder {
        val binding = ItemServicesFlightBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FlightViewHolder(binding)
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