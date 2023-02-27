package com.company.baluairlines.buy_ticket_feature.presentation.fragments

import android.os.Bundle
import android.view.*
import android.widget.ArrayAdapter
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import com.company.baluairlines.core.MainActivity
import com.company.baluairlines.core.data.mappers.toFlightInfo
import com.company.myapplication.R
import com.company.myapplication.databinding.FragmentSearchTicketsBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class SearchTicketFragment : Fragment(), MenuProvider {

    private var _binding: FragmentSearchTicketsBinding? = null
    private val binding get() = _binding!!

    private lateinit var adapter2: ArrayAdapter<String>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentSearchTicketsBinding.inflate(inflater, container, false)

        lateinit var array: MutableList<String>
//        CoroutineScope(Dispatchers.Main).launch {
//            val flights = (requireActivity() as MainActivity).appComponent.api.getFlights().body()!!
//
//
//            array = mutableListOf()
//            flights.forEach {
//                it.flights.forEach {
//                    val test = it.toFlightInfo()
//                    array.add(test.aircraftCode)
//                }
//
//            }
//            adapter2 =
//                ArrayAdapter<String>(requireContext(), android.R.layout.simple_list_item_1, array)
//            withContext(Dispatchers.Main) {
//                binding.listView.adapter = adapter2
//            }
//        }



        return binding.root
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