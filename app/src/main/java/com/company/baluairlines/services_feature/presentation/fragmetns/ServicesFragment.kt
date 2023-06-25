package com.company.baluairlines.services_feature.presentation.fragmetns

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import com.company.myapplication.R
import com.company.myapplication.databinding.FragmentServicesBinding
import com.google.android.material.transition.MaterialElevationScale
import com.google.android.material.transition.MaterialFadeThrough

class ServicesFragment : Fragment(), MenuProvider {

    private var _binding: FragmentServicesBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enterTransition = MaterialFadeThrough()
        exitTransition = MaterialElevationScale( false)
        reenterTransition = MaterialElevationScale( true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentServicesBinding.inflate(inflater, container, false)

        setupNavigation()

        return binding.root
    }

    private fun setupNavigation() {
        binding.apply {
            flightTableContainer.setOnClickListener {
                val extras = FragmentNavigatorExtras(flightTableContainer to "shared_flight_table_container")
                findNavController().navigate(R.id.action_servicesFragment_to_flightTableFragment, null, null, extras)
            }
            registrationContainer.setOnClickListener {
                val extras = FragmentNavigatorExtras(registrationContainer to "shared_flight_registration_container")
                findNavController().navigate(R.id.action_servicesFragment_to_registrationFragment, null,null,extras)
            }
            bookingStatusContainer.setOnClickListener {
                val extras = FragmentNavigatorExtras(bookingStatusContainer to "shared_booking_status_container")
                findNavController().navigate(R.id.action_servicesFragment_to_bookingStatusFragment, null,null,extras)
            }
            flightStatusContainer.setOnClickListener {
                val extras = FragmentNavigatorExtras(flightStatusContainer to "shared_flight_info_container")
                findNavController().navigate(R.id.action_servicesFragment_to_flightStatusFragment, null,null,extras)
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(this, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {}

    override fun onMenuItemSelected(menuItem: MenuItem) = true

}