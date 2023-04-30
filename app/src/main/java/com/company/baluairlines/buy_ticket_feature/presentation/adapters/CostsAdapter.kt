package com.company.baluairlines.buy_ticket_feature.presentation.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.company.baluairlines.buy_ticket_feature.di.SearchResultFragmentScope
import com.company.baluairlines.buy_ticket_feature.presentation.viewmodels.SearchResultViewModel
import com.company.baluairlines.core.domain.CostItem
import com.company.myapplication.databinding.ItemFlightCostBinding
import javax.inject.Inject

/**
 * класс адаптера для recyclerView с полетами
 * @property flightCostList содержит элементы для ресайклера
 */
@SearchResultFragmentScope
class CostsAdapter @Inject constructor(
    val context: Context,
    val viewModel: SearchResultViewModel
): RecyclerView.Adapter<CostViewHolder>()  {

    var flightCostList: List<CostItem> = emptyList()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CostViewHolder {
        val binding = ItemFlightCostBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CostViewHolder(binding, viewModel, context)
    }

    override fun onBindViewHolder(holder: CostViewHolder, position: Int) {
        val currentItem = flightCostList[position]
        holder.bind(currentItem)
    }

    /**
     * функция возврата количества элементов в листе
     * @return количество элементов в листе
     */
    override fun getItemCount(): Int {
        return flightCostList.size
    }
}