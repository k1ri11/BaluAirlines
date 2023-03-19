package com.company.baluairlines.services_feature.presentation.fragmetns

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.company.myapplication.R
import com.company.myapplication.databinding.FragmentFlightStatusBinding
import com.google.android.material.button.MaterialButton
import java.text.SimpleDateFormat
import java.util.*

class FlighStatusFragment : Fragment() {

    private var _binding: FragmentFlightStatusBinding? = null
    private val binding get() = _binding!!
    private val calendar = Calendar.getInstance()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentFlightStatusBinding.inflate(inflater, container, false)
        binding.toolbar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }
        setupDatePicker()
        setupButtonListeners()
        return binding.root
    }

    private fun setFindButtonListener() {
        binding.findButton.setOnClickListener {
            when (binding.toggleGroup.checkedButtonId) {
                R.id.route_button -> {
                    checkRouteStatesAndNavigate()
                }
                R.id.flight_number_button -> {
                    checkFlightNumberAndNavigate()
                }
            }
        }
    }

    private fun checkFlightNumberAndNavigate() {
        val flightNumber = binding.flightNumberEditText.text.toString()
        val departureDate = binding.dateEditText.text.toString()
        if (flightNumber.isEmpty()) {
            Toast.makeText(requireContext(), "Введите номер самолета", Toast.LENGTH_SHORT).show()
        } else {
            val action =
                FlighStatusFragmentDirections.actionFlightStatusFragmentToFlightStatusListFragment(
                    flightNumber = flightNumber, departureDate = departureDate
                )
            findNavController().navigate(action)
        }
    }

    private fun setupDatePicker() {
        binding.dateEditText.setText(dateToString(calendar.time))
        val dateSetListener =
            DatePickerDialog.OnDateSetListener { _, year, monthOfYear, dayOfMonth ->
                calendar.set(Calendar.YEAR, year)
                calendar.set(Calendar.MONTH, monthOfYear)
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                binding.dateEditText.setText(dateToString(calendar.time))
            }
        binding.dateEditText.setOnClickListener {
            DatePickerDialog(
                requireContext(), dateSetListener,
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
            ).show()
        }
    }

    private fun dateToString(date: Date): String {
        val format = "dd.MM.yyyy"
        val dateFormat = SimpleDateFormat(format, Locale.getDefault())
        return dateFormat.format(date)
    }

    private fun checkRouteStatesAndNavigate() {
        val departureAirport = binding.departureEditText.text.toString()
        val arrivalAirport = binding.arrivalEditText.text.toString()
        val departureDate = binding.dateEditText.text.toString()
        if (departureAirport.isEmpty() || arrivalAirport.isEmpty()) {
            Toast.makeText(
                requireContext(),
                "Введите аэропорт отправления и прибытия",
                Toast.LENGTH_SHORT
            ).show()
        } else {
            val action =
                FlighStatusFragmentDirections.actionFlightStatusFragmentToFlightStatusListFragment(
                    departureAirport = departureAirport,
                    arrivalAirport = arrivalAirport,
                    departureDate = departureDate
                )
            findNavController().navigate(action)
        }
    }

    private fun setupButtonListeners() {
        setFindButtonListener()
        setSwapButtonListener()
        binding.flightNumberButton.isChecked = true
        binding.flightNumberButton.addOnCheckedChangeListener { button, isChecked ->
            toggleButton(button, isChecked)
        }
        binding.routeButton.addOnCheckedChangeListener { button, isChecked ->
            toggleButton(button, isChecked)
        }
    }

    private fun setSwapButtonListener() {
        binding.apply {
            swapButton.setOnClickListener {
                val tmp = departureEditText.text.toString()
                departureEditText.setText(arrivalEditText.text.toString())
                arrivalEditText.setText(tmp)
            }
        }
    }

    private fun toggleButton(button: MaterialButton, isChecked: Boolean) {
        if (isChecked) {
            button.setBackgroundColor(requireContext().getColor(R.color.background_fill_dark))
        } else {
            button.setBackgroundColor(requireContext().getColor(R.color.background_primary_dark))
        }
        when (button.id) {
            R.id.route_button -> {
                binding.departureEditText.visibility = View.VISIBLE
                binding.arrivalEditText.visibility = View.VISIBLE
                binding.swapButton.visibility = View.VISIBLE
                binding.flightNumberEditText.visibility = View.GONE
            }
            R.id.flight_number_button -> {
                binding.flightNumberEditText.visibility = View.VISIBLE
                binding.departureEditText.visibility = View.GONE
                binding.arrivalEditText.visibility = View.GONE
                binding.swapButton.visibility = View.GONE
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}