package com.company.baluairlines.core.data.mappers

import com.company.baluairlines.core.data.model.FlightInfoReq
import com.company.baluairlines.core.data.model.FlightReq
import com.company.baluairlines.core.domain.Flight
import com.company.baluairlines.core.domain.FlightInfo
import java.text.SimpleDateFormat
import java.util.*

fun FlightInfoReq.toFlightInfo(): FlightInfo {
    val formatter = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ", Locale.ROOT)
    return FlightInfo(
        flightNo = flightNo,
        scheduledArrival = formatter.parse(scheduledArrival)!!,
        scheduledDeparture = formatter.parse(scheduledDeparture)!!,
        arrivalAirport = arrivalAirport,
        departureAirport = departureAirport,
        status = status,
        aircraftCode = aircraftCode,
        actualArrival = actualArrival?.let { formatter.parse(it) },
        actualDeparture = actualDeparture?.let { formatter.parse(it) }
    )
}


fun FlightReq.toFlight(): Flight {
    return Flight(
        cost = cost,
        time = time,
        flights = flights.map { it.toFlightInfo() },
    )
}