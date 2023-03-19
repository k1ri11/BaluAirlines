package com.company.baluairlines.services_feature.presentation.fragmetns

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.company.baluairlines.core.MainActivity
import com.company.baluairlines.services_feature.di.ServicesFlightComponent
import com.company.baluairlines.services_feature.di.ServicesFlightViewComponent
import com.company.baluairlines.services_feature.presentation.controllers.ServicesFlightListController
import com.company.baluairlines.services_feature.presentation.viewmodels.StatusViewModel
import com.company.myapplication.databinding.FragmentServicesFlightListBinding


class ServicesFlightListFragment : Fragment() {


    private var _binding: FragmentServicesFlightListBinding? = null
    private val binding get() = _binding!!

    private val args by navArgs<ServicesFlightListFragmentArgs>()

    private lateinit var servicesFlightComponent: ServicesFlightComponent
    private lateinit var servicesFlightViewComponent: ServicesFlightViewComponent
    private lateinit var servicesFlightListController: ServicesFlightListController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val appComponent = (activity as MainActivity).appComponent
        val viewModel: StatusViewModel by viewModels { appComponent.getViewModelFactory() }
        servicesFlightComponent = appComponent.servicesFlightComponent().create(this, viewModel)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentServicesFlightListBinding.inflate(inflater, container, false)
        servicesFlightViewComponent =
            servicesFlightComponent.servicesFlightListViewComponent().create(
                root = binding,
                viewLifecycleOwner = viewLifecycleOwner
            )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        servicesFlightListController = servicesFlightViewComponent.servicesFlightListController
        servicesFlightListController.setupViews(
            flightNumber = args.flightNumber,
            bookingNumber = args.bookingNumber,
            departureAirport = args.departureAirport,
            arrivalAirport = args.arrivalAirport,
            departureDate = args.departureDate
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}