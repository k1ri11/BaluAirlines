package com.company.baluairlines.core.data.model


import com.google.gson.annotations.SerializedName


/**
 * класс содержащий информацию о перелете (класс запроса)
 * @property serviceClass класс обслуживания в полете
 * @property passengers количество пассажиров
 * @property cost стоимость для текущего количесва пассажиров
 * @property time продолжительность полета
 * @property flights массив доступных рейсов
 */
data class FlightReq(
    @SerializedName("fare_condition")
    val serviceClass: String,
    @SerializedName("num_of_passengers")
    val passengers: Int,
    @SerializedName("cost")
    val cost: Double,
    @SerializedName("time")
    val time: String,
    @SerializedName("flights")
    val flights: List<FlightInfoReq>,
)