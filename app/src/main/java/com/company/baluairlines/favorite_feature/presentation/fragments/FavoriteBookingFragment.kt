package com.company.baluairlines.favorite_feature.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.company.baluairlines.core.MainActivity
import com.company.baluairlines.core.di.AppComponent
import com.company.baluairlines.favorite_feature.presentation.adapters.FavoriteBookingAdapter
import com.company.myapplication.databinding.FragmentFavoriteBookingBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FavoriteBookingFragment : Fragment() {

    private var _binding: FragmentFavoriteBookingBinding? = null
    private val binding get() = _binding!!
    private lateinit var appComponent: AppComponent
    private lateinit var favouriteAdapter: FavoriteBookingAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        favouriteAdapter = FavoriteBookingAdapter(requireContext())
        appComponent = (activity as MainActivity).appComponent
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoriteBookingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lifecycleScope.launch(Dispatchers.IO) {
            val bookings = appComponent.dao.getTicketsWithFlights()
            favouriteAdapter.bookings = bookings
            withContext(Dispatchers.Main){
                favouriteAdapter.notifyDataSetChanged()
            }

        }
        setupRecycler()
    }

    private fun setupRecycler() {
        binding.bookingRv.apply {
            adapter = favouriteAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}