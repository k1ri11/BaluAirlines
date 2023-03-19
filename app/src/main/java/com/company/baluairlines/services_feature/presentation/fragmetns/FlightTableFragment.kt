package com.company.baluairlines.services_feature.presentation.fragmetns

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.company.myapplication.databinding.FragmentFlightTableBinding
import java.text.SimpleDateFormat
import java.util.*

class FlightTableFragment : Fragment() {


    private var _binding: FragmentFlightTableBinding? = null
    private val binding get() = _binding!!
    private val calendar = Calendar.getInstance()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentFlightTableBinding.inflate(inflater, container, false)

        binding.toolbar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }
        setFindButtonListener()
        setupDatePicker()
        return binding.root
    }

    private fun setFindButtonListener() {
        binding.findButton.setOnClickListener {
            val departureAirport = binding.departureEditText.text.toString()
            val departureDate = binding.dateEditText.text.toString()
            if (departureAirport.isEmpty()) {
                Toast.makeText(requireContext(), "Введите аэропорт отправления", Toast.LENGTH_SHORT)
                    .show()
            } else {
                val action = FlightTableFragmentDirections.flightTableFrToFlightStatusListFr(
                    departureAirport = departureAirport,
                    departureDate = departureDate
                )
                findNavController().navigate(action)
            }
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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}