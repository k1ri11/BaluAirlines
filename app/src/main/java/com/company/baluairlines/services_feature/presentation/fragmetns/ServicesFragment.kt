package com.company.baluairlines.services_feature.presentation.fragmetns

import android.os.Bundle
import android.view.*
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.navigation.fragment.findNavController
import com.company.myapplication.R
import com.company.myapplication.databinding.FragmentServicesBinding

class ServicesFragment : Fragment(), MenuProvider {

    private var _binding: FragmentServicesBinding? = null
    private val binding get() = _binding!!

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
                findNavController().navigate(R.id.action_servicesFragment_to_flightTableFragment)
            }
            registrationContainer.setOnClickListener {
                findNavController().navigate(R.id.action_servicesFragment_to_registrationFragment)
            }
            bookingStatusContainer.setOnClickListener {
                findNavController().navigate(R.id.action_servicesFragment_to_bookingStatusFragment)
            }
            flightStatusContainer.setOnClickListener {
                findNavController().navigate(R.id.action_servicesFragment_to_flightStatusFragment)
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