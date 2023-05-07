package com.company.baluairlines.core.domain


import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.util.*

/**
 * класс содержащий информацию о перелете
 * @property flightNo номер рейса
 * @property scheduledArrival запланированное время прилета
 * @property scheduledArrivalTime запланированное время прилета в формате HH:mm
 * @property scheduledDeparture запланированное время отправление
 * @property scheduledDepartureTime запланированное время отправление в формате HH:mm
 * @property arrivalAirport аэропорт прилета
 * @property departureAirport аэропорт вылета
 * @property status текущий статус рейса
 * @property aircraftCode номер самолета
 * @property actualArrival фактическое время прилета
 * @property actualDeparture фактическое время вылета
 */
@Parcelize
data class FlightInfo(
    val flightId: Int,
    val flightNo: String,
    val scheduledArrival: Date,
    val scheduledArrivalTime: String,
    val scheduledDeparture: Date,
    val scheduledDepartureTime: String,
    val arrivalAirport: String,
    val departureAirport: String,
    val status: String,
    val aircraftCode: String,
    val actualArrival: Date? = null,
    val actualDeparture: Date? = null,
) : Parcelable

