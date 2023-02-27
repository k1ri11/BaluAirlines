package com.company.baluairlines.services_feature.presentation.fragmetns

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.company.myapplication.databinding.FragmentFlightStatusListBinding


class FlightStatusListFragment : Fragment() {


    private var _binding: FragmentFlightStatusListBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentFlightStatusListBinding.inflate(inflater, container, false)

//        binding.toolbarLayout.airports.text = requireContext().getString(R.string.departure_arrival_airports, "VVO", "SVO")
//        binding.toolbarLayout.description.text = requireContext().getString(R.string.dep_arr_date_and_passengers , 12, "февр", 17,"февр", 3)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}