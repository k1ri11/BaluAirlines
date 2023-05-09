package com.company.baluairlines.buy_ticket_feature.presentation.controllers

import android.app.Dialog
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.fragment.NavHostFragment.Companion.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.company.baluairlines.buy_ticket_feature.presentation.adapters.FlightDetailsAdapter
import com.company.baluairlines.buy_ticket_feature.presentation.viewmodels.FlightDetailsViewModel
import com.company.baluairlines.core.domain.Flight
import com.company.baluairlines.core.utils.Resource
import com.company.myapplication.NavGraphDirections
import com.company.myapplication.databinding.DialogPersonalInformationBinding
import com.company.myapplication.databinding.FragmentFlightDetailsBinding
import javax.inject.Inject

class FlightDetailsController @Inject constructor(
    private val fragment: Fragment,
    private val binding: FragmentFlightDetailsBinding,
    private val flightDetailsAdapter: FlightDetailsAdapter,
    private val viewLifecycleOwner: LifecycleOwner,
    private val viewModel: FlightDetailsViewModel,
) {

    fun setupUi(flight: Flight) {
        setupRecycler(flight)
        setupBuyClickListener()
    }

    private fun setupBuyClickListener() {
        binding.buyButton.setOnClickListener {
            val dialog = Dialog(
                fragment.requireContext(),
                com.google.android.material.R.style.ThemeOverlay_Material3_DayNight_BottomSheetDialog
            )
            val dialogBinding =
                DialogPersonalInformationBinding.inflate(LayoutInflater.from(fragment.requireContext()))
            dialog.setContentView(dialogBinding.root)

            dialogBinding.saveButton.setOnClickListener {
                setupSaveButtonClickListener(dialogBinding, dialog)
            }
            dialog.show()
        }
    }

    private fun setupSaveButtonClickListener(
        dialogBinding: DialogPersonalInformationBinding,
        dialog: Dialog
    ) {
        val passengerName = dialogBinding.passengerNameEditText.text.toString()
        val phone = dialogBinding.phoneEditText.text.toString()
        val email = dialogBinding.emailEditText.text.toString()
        val passport = dialogBinding.passengerPassportEditText.text.toString()
        val serviceClass = flightDetailsAdapter.serviceClassInfo
        val flights = mutableListOf<Int>()
        flightDetailsAdapter.flightInfoList.forEach { flights.add(it.flightId) }
        if (passengerName.isNotEmpty() && phone.isNotEmpty() && email.isNotEmpty()) {
            viewModel.sendPersonalInformation(
                passengerName = passengerName,
                phone = phone,
                email = email,
                flightService = serviceClass,
                passport = passport,
                flights = flights
            )
            setupBookingStatusObserver()
            dialog.dismiss()
        } else {
            Toast.makeText(fragment.requireContext(), "Заполните все поля", Toast.LENGTH_SHORT)
                .show()
        }
    }

    private fun setupBookingStatusObserver() {
        viewModel.bookingStatus.observe(viewLifecycleOwner){resourse ->
            when(resourse){
                is Resource.Loading -> {
                    showProgressBar()
                }
                is Resource.Success -> {
                    hideProgressBar()
                    val action = NavGraphDirections.actionGlobalBookingDetailsFragment(resourse.data!!.bookRef)
                    findNavController(fragment).navigate(action)
                }
                is Resource.Error -> {
                    hideProgressBar()
                }

            }

        }
    }

    private fun showProgressBar() {
        binding.progressBar.visibility = View.VISIBLE
    }

    private fun hideProgressBar() {
        binding.progressBar.visibility = View.GONE
    }

    private fun setupRecycler(flight: Flight) {
        binding.detailsRv.apply {
            adapter = flightDetailsAdapter
            layoutManager = LinearLayoutManager(fragment.requireContext())
        }
        flightDetailsAdapter.flightInfoList = flight.flights
        flightDetailsAdapter.serviceClassInfo = flight.serviceClass
        flightDetailsAdapter.notifyDataSetChanged()
    }
}