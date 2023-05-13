package com.company.baluairlines.services_feature.presentation.fragmetns

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.company.baluairlines.core.MainActivity
import com.company.baluairlines.core.domain.Booking
import com.company.baluairlines.core.utils.Resource
import com.company.baluairlines.services_feature.presentation.viewmodels.BookingViewModel
import com.company.myapplication.R
import com.company.myapplication.databinding.FragmentBookingDetailsBinding
import java.text.SimpleDateFormat
import java.util.*

class BookingDetailsFragment : Fragment() {

    private var _binding: FragmentBookingDetailsBinding? = null
    private val binding get() = _binding!!

    private val args by navArgs<BookingDetailsFragmentArgs>()

    private lateinit var viewModel: BookingViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentBookingDetailsBinding.inflate(inflater, container, false)

        Toast.makeText(requireContext(), args.bookingNumber, Toast.LENGTH_SHORT).show()

        val appComponent = (activity as MainActivity).appComponent
        val bookingViewModel: BookingViewModel by viewModels { appComponent.getViewModelFactory() }
        viewModel = bookingViewModel
        viewModel.getBooking(args.bookingNumber)
        observeBooking()
        binding.toolbar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }
        return binding.root
    }

    private fun observeBooking() {
        viewModel.bookingStatus.observe(viewLifecycleOwner) { resourse ->
            when (resourse) {
                is Resource.Success -> {
                    hideProgressBar()
                    binding.toolbar.title = "${resources.getString(R.string.booking)} ${resourse.data?.bookRef}"
                    updateUI(resourse.data!!)
                }
                is Resource.Error -> {
                    hideProgressBar()
                    resourse.message?.let { message ->
                        Toast.makeText(
                            requireContext(),
                            resources.getString(R.string.error).plus(message),
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
                is Resource.Loading -> {
                    showProgressBar()
                }
            }
        }
    }

    private fun updateUI(booking: Booking) {
        val formatter = SimpleDateFormat("HH:mm dd MMM, E", Locale.getDefault())
        val flightInfo = booking.tickets.first().flight.first()
        binding.arrivalAirport.text = flightInfo.arrivalAirport
        binding.departureAirport.text = flightInfo.departureAirport
        binding.flightNumber.text = flightInfo.flightNo
        binding.passengers.text = booking.tickets.size.toString()
        val arrivalDate = formatter.format(flightInfo.scheduledArrival)
        binding.arrivalDate.text = arrivalDate
        val departureDate = formatter.format(flightInfo.scheduledDeparture)
        binding.departureDate.text = departureDate
    }

    private fun showProgressBar() {
        binding.progressBar.visibility = View.VISIBLE
    }

    private fun hideProgressBar() {
        binding.progressBar.visibility = View.GONE
    }
}