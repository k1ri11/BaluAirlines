package com.company.baluairlines.buy_ticket_feature.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.company.myapplication.R
import com.company.myapplication.databinding.BottomsheetPassengersBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class PassengerBottomSheet : BottomSheetDialogFragment() {

    lateinit var binding: BottomsheetPassengersBinding

    private var passengers = intArrayOf(1, 0)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = BottomsheetPassengersBinding.bind(
            inflater.inflate(
                R.layout.bottomsheet_passengers,
                container
            )
        )
        setupListeners()
        isCancelable = true
        updateUICounter(2)
        return binding.root
    }

    private fun setupListeners() {
        binding.adultPlusButton.setOnClickListener {
            passengers[0]++
            updateUICounter(0)
        }
        binding.adultMinusButton.setOnClickListener {
            if (passengers[0] > 0) {
                passengers[0]--
                updateUICounter(0)
            }
        }

        binding.childrenPlusButton.setOnClickListener {
            passengers[1]++
            updateUICounter(1)
        }
        binding.childrenMinusButton.setOnClickListener {
            if (passengers[1] > 0) {
                passengers[1]--
                updateUICounter(1)
            }
        }
    }

    private fun updateUICounter(index: Int){
        when(index){
            0 ->{binding.adultPassengerCounter.text = passengers[index].toString()}
            1 ->{binding.childrenPassengerCounter.text = passengers[index].toString()}
            2 -> {
                for (i in 0..1){updateUICounter(i)}
            }
        }
    }

    fun getTotalPassengers(): IntArray {
        return passengers
    }

    fun setPassengers(pas: IntArray){
        passengers = pas
    }

}
