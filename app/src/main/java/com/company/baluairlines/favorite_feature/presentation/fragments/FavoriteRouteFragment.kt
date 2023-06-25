package com.company.baluairlines.favorite_feature.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.company.baluairlines.core.MainActivity
import com.company.baluairlines.favorite_feature.presentation.adapters.FavoriteRoutesAdapter
import com.company.myapplication.databinding.FragmentFavoriteRouteBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FavoriteRouteFragment : Fragment() {

    private var _binding: FragmentFavoriteRouteBinding? = null
    private val binding get() = _binding!!
    private val routesAdapter = FavoriteRoutesAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val appComponent = (activity as MainActivity).appComponent
        lifecycleScope.launch(Dispatchers.IO) {
            val routes = appComponent.dao.getRoutes()
            routesAdapter.routes = routes
            withContext(Dispatchers.Main){
                routesAdapter.notifyDataSetChanged()
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoriteRouteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecycler()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setupRecycler() {
        binding.routesRv.apply {
            adapter = routesAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
    }
}