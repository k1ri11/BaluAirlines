package com.company.baluairlines.core.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


/**
 * класс содержащий информацию о перелете (для UI)
 * @property serviceClass класс обслуживания в полете
 * @property passengers количество пассажиров
 * @property cost стоимость для текущего количесва пассажиров
 * @property time продолжительность полета
 * @property flights массив доступных рейсов
 */
@Parcelize
data class Flight(
    val serviceClass: ServiceClass,
    val passengers: Int,
    val cost: Int,
    val time: String,
    val flights: List<FlightInfo>,
): Parcelable