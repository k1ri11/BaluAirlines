package com.company.baluairlines.buy_ticket_feature.presentation.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.company.baluairlines.core.data.mappers.serviceToString
import com.company.baluairlines.core.domain.FlightInfo
import com.company.baluairlines.core.domain.ServiceClass
import com.company.myapplication.databinding.ItemFlightDetailsBinding
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

/**
 * класс адаптера для recyclerView с полетами
 * @property flightInfoList содержит элементы для ресайклера
 */
class FlightDetailsAdapter @Inject constructor(
    private val context: Context
) : RecyclerView.Adapter<FlightDetailsAdapter.FlightDetailsViewHolder>() {

    var flightInfoList = emptyList<FlightInfo>()
    var serviceClassInfo: ServiceClass = ServiceClass.Economy

    inner class FlightDetailsViewHolder(
        private val binding: ItemFlightDetailsBinding,
        private val context: Context
    ) : RecyclerView.ViewHolder(binding.root) {

        private val formatter = SimpleDateFormat("HH:mm dd MMM, E", Locale.getDefault())

        /**
         * функция заполнения элемента ресайклера
         * @param currentItem текущий элемент
         */
        fun bind(currentItem: FlightInfo) {
            binding.apply {
                flightNumber.text = currentItem.flightNo
                departureDate.text = formatter.format(currentItem.scheduledDeparture)
                departureAirport.text = currentItem.departureAirport
                arrivalDate.text = formatter.format(currentItem.scheduledArrival)
                arrivalAirport.text = currentItem.arrivalAirport
                serviceClass.text = serviceClassInfo.serviceToString()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FlightDetailsViewHolder {
        val binding =
            ItemFlightDetailsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FlightDetailsViewHolder(binding, context)
    }

    override fun onBindViewHolder(holder: FlightDetailsViewHolder, position: Int) {
        val currentItem = flightInfoList[position]
        holder.bind(currentItem)
    }

    /**
     * функция возврата количества элементов в листе
     * @return количество элементов в листе
     */
    override fun getItemCount(): Int {
        return flightInfoList.size
    }
}