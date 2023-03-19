package com.company.baluairlines.services_feature.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.company.baluairlines.core.domain.FlightInfo
import com.company.baluairlines.services_feature.di.ServicesFlightFragmentScope
import com.company.myapplication.databinding.ItemServicesFlightBinding
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject



@ServicesFlightFragmentScope
class ServicesFlightAdapter @Inject constructor() : RecyclerView.Adapter<ServicesFlightAdapter.FlightViewHolder>() {

    var FlightList: List<FlightInfo> = emptyList()
        set(newValue) {
            val diffCallback = FlightDiffUtilCallback(field, newValue)
            val diffResult = DiffUtil.calculateDiff(diffCallback)
            field = newValue
            diffResult.dispatchUpdatesTo(this)
        }

    inner class FlightViewHolder(private val binding: ItemServicesFlightBinding) :
        RecyclerView.ViewHolder(binding.root) {

        val formatter = SimpleDateFormat("HH:mm", Locale.ROOT)

        fun bind(currentItem: FlightInfo) {
            binding.apply {
                departureTime.text = formatter.format(currentItem.scheduledDeparture)
                arrivalTime.text = formatter.format(currentItem.scheduledArrival)
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
        val currentItem = FlightList[position]
        holder.bind(currentItem)
    }

    override fun getItemCount(): Int {
        return FlightList.size
    }


}