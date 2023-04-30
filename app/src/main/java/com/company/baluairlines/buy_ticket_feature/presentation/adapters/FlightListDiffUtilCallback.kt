package com.company.baluairlines.buy_ticket_feature.presentation.adapters

import androidx.recyclerview.widget.DiffUtil
import com.company.baluairlines.core.domain.Flight

class FlightListDiffUtilCallback(
    private var oldList: List<Flight>,
    private var newList: List<Flight>,
) : DiffUtil.Callback() {
    /** функция для возвращения размера старого листа
     * @return размер листа
     */
    override fun getOldListSize(): Int = oldList.size

    /** функция для возвращения размера нового листа
     * @return размер листа
     */
    override fun getNewListSize(): Int = newList.size

    /**
     * функция для определения одинаковые ли объекты
     * @return одинаковые ли items
     */
    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem = oldList[oldItemPosition]
        val newItem = newList[newItemPosition]
        return oldItem.cost == newItem.cost && oldItem.time == newItem.time
    }

    /**
     * функция для определения одинаковые ли содержимое объекта
     * @return одинаковое ли содержимое объекта
     */
    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem = oldList[oldItemPosition]
        val newItem = newList[newItemPosition]
        return oldItem == newItem
    }
}