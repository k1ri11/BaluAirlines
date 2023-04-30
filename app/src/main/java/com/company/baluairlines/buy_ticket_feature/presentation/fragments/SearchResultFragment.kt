package com.company.baluairlines.buy_ticket_feature.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.company.baluairlines.buy_ticket_feature.di.SearchResultComponent
import com.company.baluairlines.buy_ticket_feature.di.SearchResultViewComponent
import com.company.baluairlines.buy_ticket_feature.presentation.controllers.SearchResultController
import com.company.baluairlines.buy_ticket_feature.presentation.viewmodels.SearchResultViewModel
import com.company.baluairlines.core.MainActivity
import com.company.baluairlines.core.domain.FlightUIState
import com.company.myapplication.databinding.FragmentSearchResultBinding

class SearchResultFragment : Fragment() {

    private var _binding: FragmentSearchResultBinding? = null
    private val binding get() = _binding!!

    private val args by navArgs<SearchResultFragmentArgs>()

    private lateinit var searchResultComponent: SearchResultComponent
    private lateinit var searchResultViewComponent: SearchResultViewComponent
    private lateinit var searchResultController: SearchResultController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val appComponent = (activity as MainActivity).appComponent
        val viewModel: SearchResultViewModel by viewModels { appComponent.getViewModelFactory() }
        searchResultComponent = appComponent.searchResultComponent().create(this, viewModel)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchResultBinding.inflate(inflater, container, false)
        searchResultViewComponent =
            searchResultComponent.searchResultViewComponent().create(
                root = binding,
                viewLifecycleOwner = viewLifecycleOwner
            )

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        searchResultController = searchResultViewComponent.searchResultController
        searchResultController.setupViews(
            FlightUIState(
                date = args.date,
                serviceClass = args.serviceClass,
                departureAirport = args.departureAirport,
                arrivalAirport = args.arrivalAirport,
                passengers = args.passengers
            )
        )
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}