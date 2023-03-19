package com.company.baluairlines.services_feature.presentation.adapter

import androidx.recyclerview.widget.DiffUtil
import com.company.baluairlines.core.domain.FlightInfo

class FlightDiffUtilCallback(
    private var oldList: List<FlightInfo>,
    private var newList: List<FlightInfo>,
) : DiffUtil.Callback() {
    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem = oldList[oldItemPosition]
        val newItem = newList[newItemPosition]
        return oldItem.flightNo == newItem.flightNo
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem = oldList[oldItemPosition]
        val newItem = newList[newItemPosition]
        return oldItem == newItem
    }
}