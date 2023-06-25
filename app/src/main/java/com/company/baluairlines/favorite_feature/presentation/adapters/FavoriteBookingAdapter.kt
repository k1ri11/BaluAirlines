package com.company.baluairlines.favorite_feature.presentation.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.company.baluairlines.core.data.database.TicketsWithFlights
import com.company.myapplication.R
import com.company.myapplication.databinding.ItemFavoriteBookingBinding
import java.text.SimpleDateFormat
import java.util.Locale

class FavoriteBookingAdapter(
    private val context: Context
) :
    RecyclerView.Adapter<FavoriteBookingAdapter.FavoriteBookingViewHolder>() {

    var bookings: List<TicketsWithFlights> = emptyList()

    inner class FavoriteBookingViewHolder(
        private val binding: ItemFavoriteBookingBinding,
    ) : RecyclerView.ViewHolder(binding.root) {

        private val formatter = SimpleDateFormat("HH:mm dd MMM, E", Locale.getDefault())

        fun bind(currentItem: TicketsWithFlights) {
            if (currentItem.flights.isNotEmpty()){
                val departureFlight = currentItem.flights.first()
                val arrivalFlight = currentItem.flights.last()
                val departureDateStr = formatter.format(departureFlight.scheduledDeparture)
                val arrivalDateStr = formatter.format(arrivalFlight.scheduledArrival)
                binding.apply {
                    bookingNumber.text = currentItem.ticket.bookRef
                    bookingOwner.text = context.getString(R.string.booking_owner_name, currentItem.ticket.passengerName)
                    departureDate.text = departureDateStr
                    departureAirport.text = departureFlight.departureAirport
                    arrivalDate.text = arrivalDateStr
                    arrivalAirport.text = arrivalFlight.arrivalAirport
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteBookingViewHolder {
        val binding =
            ItemFavoriteBookingBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FavoriteBookingViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: FavoriteBookingViewHolder,
        position: Int
    ) {
        val currentItem = bookings[position]
        holder.bind(currentItem)
    }

    override fun getItemCount(): Int = bookings.size
}