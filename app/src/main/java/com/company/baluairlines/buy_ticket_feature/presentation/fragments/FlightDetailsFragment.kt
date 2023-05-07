package com.company.baluairlines.buy_ticket_feature.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.company.baluairlines.buy_ticket_feature.di.FlightDetailsComponent
import com.company.baluairlines.buy_ticket_feature.di.FlightDetailsViewComponent
import com.company.baluairlines.buy_ticket_feature.presentation.controllers.FlightDetailsController
import com.company.baluairlines.buy_ticket_feature.presentation.viewmodels.FlightDetailsViewModel
import com.company.baluairlines.core.MainActivity
import com.company.myapplication.databinding.FragmentFlightDetailsBinding


class FlightDetailsFragment : Fragment() {

    private var _binding: FragmentFlightDetailsBinding? = null
    private val binding get() = _binding!!

    private val args by navArgs<FlightDetailsFragmentArgs>()

    private lateinit var flightDetailsComponent: FlightDetailsComponent
    private lateinit var flightDetailsViewComponent: FlightDetailsViewComponent
    private lateinit var flightDetailsController: FlightDetailsController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val appComponent = (activity as MainActivity).appComponent
        val viewModel: FlightDetailsViewModel by viewModels { appComponent.getViewModelFactory() }
        flightDetailsComponent = appComponent.flightDetailsComponent().create(this, viewModel)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFlightDetailsBinding.inflate(inflater, container, false)
        flightDetailsViewComponent =
            flightDetailsComponent.flightDetailsViewComponent().create(
                root = binding,
                viewLifecycleOwner = viewLifecycleOwner
            )

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        flightDetailsController = flightDetailsViewComponent.flightDetailsController
        flightDetailsController.setupUi(args.flight)
    }

}