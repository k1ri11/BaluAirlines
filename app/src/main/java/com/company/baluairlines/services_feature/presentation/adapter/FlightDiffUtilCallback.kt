package com.company.baluairlines.services_feature.presentation.adapter

import androidx.recyclerview.widget.DiffUtil
import com.company.baluairlines.core.domain.FlightInfo

/**
 * класс для расчета изменения между старым и новым листом
 * @param oldList старый лист с данными
 * @param newList новый лист с данными
 */
class FlightDiffUtilCallback(
    private var oldList: List<FlightInfo>,
    private var newList: List<FlightInfo>,
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
        return oldItem.flightNo == newItem.flightNo
    }

    /**
     * функция для определения одинаковые ли содержимое объекта
     * @return одинаковое ли содержимое класса
     */
    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem = oldList[oldItemPosition]
        val newItem = newList[newItemPosition]
        return oldItem == newItem
    }
}