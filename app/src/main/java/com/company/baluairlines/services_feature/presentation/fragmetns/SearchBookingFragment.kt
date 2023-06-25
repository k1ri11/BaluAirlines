package com.company.baluairlines.services_feature.presentation.fragmetns

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.company.myapplication.R
import com.company.myapplication.ServicesGraphDirections
import com.company.myapplication.databinding.FragmentSearchBookingBinding
import com.google.android.material.transition.MaterialContainerTransform

class SearchBookingFragment : Fragment() {

    private var _binding: FragmentSearchBookingBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedElementEnterTransition = MaterialContainerTransform()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentSearchBookingBinding.inflate(inflater, container, false)

        binding.toolbar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }

        binding.searchButton.setOnClickListener {
            val bookingNumber = binding.bookingNumberEditText.text.toString()
            if (bookingNumber.isNotEmpty()) {
                val action = ServicesGraphDirections.actionGlobalBookingDetailsFragment3(bookingNumber)
                findNavController().navigate(action)
            }
            else{
                Toast.makeText(requireContext(), resources.getString(R.string.booking_number_hint), Toast.LENGTH_SHORT).show()}
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}