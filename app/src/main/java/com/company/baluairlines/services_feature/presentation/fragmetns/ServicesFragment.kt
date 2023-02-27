package com.company.baluairlines.services_feature.presentation.fragmetns

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.company.baluairlines.core.MainActivity
import com.company.baluairlines.core.utils.Resource
import com.company.baluairlines.services_feature.domain.ServicesRepositoryImpl
import com.company.baluairlines.services_feature.presentation.viewmodels.StatusViewModel
import com.company.myapplication.R
import com.company.myapplication.databinding.FragmentServicesBinding
import kotlinx.coroutines.launch
import java.util.*

class ServicesFragment : Fragment(), MenuProvider {

    private var _binding: FragmentServicesBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentServicesBinding.inflate(inflater, container, false)

        val appcomp = (requireActivity() as MainActivity).appComponent
        val viewmodel: StatusViewModel = StatusViewModel(
            ServicesRepositoryImpl(
                api = appcomp.api,
                context = requireContext(),
                networkUtils = appcomp.networkUtils
            )
        )
//        viewLifecycleOwner.lifecycleScope.launch {
//            viewmodel.flightStatusWithNumber("PG0404", Calendar.getInstance().timeInMillis)
//            viewmodel.flightStatus.observe(viewLifecycleOwner) {
//                if (it is Resource.Success) {
//                    Log.d("fffff", "onCreateView: ${it.data?.status} ")
//                }
//                if (it is Resource.Error) {
//                    Log.d("fffff", "onCreateView: ${it.message} ")
//                }
//            }
//
//        }

        setupnavigation(binding)

        return binding.root
    }

    private fun setupnavigation(binding: FragmentServicesBinding) {
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