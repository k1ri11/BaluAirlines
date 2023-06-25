package com.company.baluairlines.services_feature.presentation.controllers

import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.LinearLayoutManager
import com.company.baluairlines.core.domain.FlightInfo
import com.company.baluairlines.core.utils.Resource
import com.company.baluairlines.services_feature.di.ServicesFlightViewScope
import com.company.baluairlines.services_feature.presentation.adapter.ServicesFlightAdapter
import com.company.baluairlines.services_feature.presentation.viewmodels.StatusViewModel
import com.company.myapplication.R
import com.company.myapplication.databinding.FragmentServicesFlightListBinding
import java.text.SimpleDateFormat
import java.util.Locale
import javax.inject.Inject

@ServicesFlightViewScope
class ServicesFlightListController @Inject constructor(
    private val fragment: Fragment,
    private val binding: FragmentServicesFlightListBinding,
    private val adapter: ServicesFlightAdapter,
    private val viewLifecycleOwner: LifecycleOwner,
    private val viewModel: StatusViewModel,
) {

    var flightNumber = ""
    var bookingNumber = ""
    var departureAirport = ""
    var arrivalAirport = ""
    var departureDate = ""
    private val departureFormatter = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault())
    private val formatter = SimpleDateFormat("dd MMMM ", Locale.getDefault())

    fun setupViews(
        flightNumber: String,
        bookingNumber: String,
        departureAirport: String,
        arrivalAirport: String,
        departureDate: String,
    ) {
        updateFields(flightNumber, bookingNumber, departureAirport, arrivalAirport, departureDate)
        searchFlights()
        setupRecycler()
        setupObserver()
    }

    private fun setupAirports(item: FlightInfo) {
        binding.departureAirport.text = item.departureAirport
        binding.arrivalAirport.text = item.arrivalAirport
        binding.date.text = formatter.format(item.scheduledDeparture)
    }

    private fun setupObserver() {
        viewModel.flightStatus.observe(viewLifecycleOwner) { response ->
            when (response) {
                is Resource.Loading -> {
                    showProgressBar()
                }
                is Resource.Success -> {
                    hideProgressBar()
                    val result = response.data!!
                    adapter.flightList = result
                    setupAirports(result[0])
                }
                is Resource.Error -> {
                    hideProgressBar()
                    response.message?.let { message ->
                        Toast.makeText(
                            fragment.requireContext(),
                            fragment.resources.getString(R.string.error).plus(message),
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
                else -> {}
            }
        }
    }

    private fun showProgressBar() {
        binding.prBar.visibility = View.VISIBLE
    }

    private fun hideProgressBar() {
        binding.prBar.visibility = View.GONE
    }

    private fun searchFlights() {
        if (flightNumber.isNotEmpty()) {
            viewModel.getFlightStatusWithNumber(
                flightNumber,
                departureFormatter.parse(departureDate)!!.time
            )
            return
        }
        if (bookingNumber.isNotEmpty()) {
//                viewModel.flightStatusWithBookingNumber(bookingNumber, Calendar.getInstance().timeInMillis)
            return
        }
        if (arrivalAirport.isNotEmpty()) {
            viewModel.getFlightStatusWithAirports(
                departureAirport = departureAirport,
                arrivalAirport = arrivalAirport,
                date = departureFormatter.parse(departureDate)!!.time
            )
            return
        } else {
            viewModel.getFlightTable(
                departureAirport = departureAirport,
                date = departureFormatter.parse(departureDate)!!.time
            )
        }
    }

    private fun updateFields(
        flightNum: String,
        bookingNum: String,
        depAirport: String,
        arrAirport: String,
        depDate: String
    ) {
        flightNumber = flightNum
        bookingNumber = bookingNum
        departureAirport = depAirport
        arrivalAirport = arrAirport
        departureDate = depDate
    }

    private fun setupRecycler() {
        binding.apply {
            servicesFlightRv.adapter = adapter
            servicesFlightRv.layoutManager = LinearLayoutManager(fragment.requireActivity())
        }
    }


}