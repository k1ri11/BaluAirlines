package com.company.baluairlines.buy_ticket_feature.presentation.controllers

import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.LinearLayoutManager
import com.company.baluairlines.buy_ticket_feature.di.SearchResultViewScope
import com.company.baluairlines.buy_ticket_feature.presentation.adapters.CostsAdapter
import com.company.baluairlines.buy_ticket_feature.presentation.adapters.FlightListAdapter
import com.company.baluairlines.buy_ticket_feature.presentation.viewmodels.SearchResultViewModel
import com.company.baluairlines.core.domain.FlightUIState
import com.company.baluairlines.core.utils.Resource
import com.company.myapplication.R
import com.company.myapplication.databinding.FragmentSearchResultBinding
import javax.inject.Inject

@SearchResultViewScope
class SearchResultController @Inject constructor(
    private val fragment: Fragment,
    private val binding: FragmentSearchResultBinding,
    private val costsAdapter: CostsAdapter,
    private val flightListAdapter: FlightListAdapter,
    private val viewLifecycleOwner: LifecycleOwner,
    private val viewModel: SearchResultViewModel,
) {

    fun setupViews(state: FlightUIState) {
        viewModel.setUIState(state)
        observeSearchResult()
        observeCostResult()
        observeUIState()
        setupAdapters()
        viewModel.getFlightCosts()
        viewModel.getFlights()
        val airports = fragment.resources.getString(R.string.departure_arrival_airports, state.departureAirport, state.arrivalAirport)
        binding.toolbar.airports.text = airports
    }

    private fun observeUIState() {
        viewModel.uiState.observe(viewLifecycleOwner) { state ->
            val dateAndPassengers = fragment.resources.getString(R.string.dep_arr_date_and_passengers, state.date, state.passengers)
            binding.toolbar.dateAndPassengers.text = dateAndPassengers
        }
    }

    private fun setupAdapters() {
        binding.costsRv.apply {
            adapter = costsAdapter
            layoutManager = LinearLayoutManager(fragment.requireContext(), LinearLayoutManager.HORIZONTAL,false)
        }
        binding.flightRv.apply {
            adapter = flightListAdapter
            layoutManager = LinearLayoutManager(fragment.requireContext())
        }
    }

    private fun observeSearchResult() {
        viewModel.searchResult.observe(viewLifecycleOwner) { response ->
            when (response) {
                is Resource.Loading -> {
                    showProgressBar()
                }
                is Resource.Success -> {
                    hideProgressBar()
                    val result = response.data!!
                    flightListAdapter.flightList = result
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
            }
        }
    }


    private fun observeCostResult() {
        viewModel.costs.observe(viewLifecycleOwner) { response ->
            when (response) {
                is Resource.Loading -> {
                    showProgressBar()
                }
                is Resource.Success -> {
                    hideProgressBar()
                    val result = response.data!!
                    costsAdapter.flightCostList = result
                    costsAdapter.notifyDataSetChanged()
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
            }
        }
    }

    private fun hideProgressBar() {
        binding.progressBar.visibility = View.GONE
    }

    private fun showProgressBar(){
        binding.progressBar.visibility = View.VISIBLE
    }

}