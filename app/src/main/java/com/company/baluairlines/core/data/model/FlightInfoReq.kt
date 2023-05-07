package com.company.baluairlines.core.data.model


import com.google.gson.annotations.SerializedName

/**
 * класс содержащий информацию о перелете (класс запроса)
 * @property flightNo номер рейса
 * @property scheduledArrival запланированное время прилета
 * @property scheduledDeparture запланированное время отправление
 * @property arrivalAirport аэропорт прилета
 * @property departureAirport аэропорт вылета
 * @property status текущий статус рейса
 * @property aircraftCode код модели самолета
 * @property actualArrival фактическое время прилета
 * @property actualDeparture фактическое время вылета
 */
data class FlightInfoReq(
    @SerializedName("flight_id")
    val flightId: Int,
    @SerializedName("flight_no")
    val flightNo: String,
    @SerializedName("scheduled_arrival")
    val scheduledArrival: String,
    @SerializedName("scheduled_departure")
    val scheduledDeparture: String,
    @SerializedName("arrival_airport")
    val arrivalAirport: String,
    @SerializedName("departure_airport")
    val departureAirport: String,
    @SerializedName("status")
    val status: String,
    @SerializedName("aircraft_code")
    val aircraftCode: String,
    @SerializedName("actual_arrival")
    val actualArrival: String? = null,
    @SerializedName("actual_departure")
    val actualDeparture: String? = null,
)