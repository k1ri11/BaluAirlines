package com.company.baluairlines.core.data.mappers

import com.company.baluairlines.core.data.model.BookingReq
import com.company.baluairlines.core.data.model.FlightInfoReq
import com.company.baluairlines.core.data.model.FlightReq
import com.company.baluairlines.core.data.model.TicketReq
import com.company.baluairlines.core.domain.*
import java.text.SimpleDateFormat
import java.util.*

/** функция для маппинга из класса запроса (FlightInfoReq) в класс для UI (FlightInfo) */
fun FlightInfoReq.toFlightInfo(): FlightInfo {
    val formatter = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ", Locale.getDefault())
    val formatterHours = SimpleDateFormat("HH:mm", Locale.getDefault())
    val scheduledArrivalDate = formatter.parse(scheduledArrival)!!
    val scheduledDepartureDate = formatter.parse(scheduledDeparture)!!

    return FlightInfo(
        flightId = flightId,
        flightNo = flightNo,
        scheduledArrival = scheduledArrivalDate,
        scheduledArrivalTime = formatterHours.format(scheduledArrivalDate),
        scheduledDeparture = scheduledDepartureDate,
        scheduledDepartureTime = formatterHours.format(scheduledDepartureDate),
        arrivalAirport = arrivalAirport,
        departureAirport = departureAirport,
        status = status,
        aircraftCode = aircraftCode,
        actualArrival = actualArrival?.let { formatter.parse(it) },
        actualDeparture = actualDeparture?.let { formatter.parse(it) }
    )
}

/** функция для маппинга из класса запроса (FlightReq) в класс для UI (Flight) */
fun FlightReq.toFlight(): Flight {
    return Flight(
        serviceClass = serviceClass.toServiceClass(),
        passengers = passengers,
        cost = cost.toInt(),
        time = time,
        flights = flights.map { it.toFlightInfo() },
    )
}

fun ServiceClass.serviceToString(): String {
    return when (this) {
        ServiceClass.Economy -> "Economy"
//            "Comfort" -> ServiceClass.Comfort
        ServiceClass.Business -> "Business"
    }
}

fun String.toServiceClass(): ServiceClass {
    return when (this) {
        "Economy" -> ServiceClass.Economy
//            "Comfort" -> ServiceClass.Comfort
        "Business" -> ServiceClass.Business
        else -> ServiceClass.Economy
    }
}

fun BookingReq.toBooking(): Booking {
    return Booking(
        bookDate = bookDate,
        bookRef = bookRef,
        totalAmount = totalAmount,
        tickets = tickets.map { it.toTicket() }
    )
}

fun TicketReq.toTicket(): Ticket {
    return Ticket(
        bookRef = bookRef,
        contactData = contactData,
        flight = flight?.map { it.toFlightInfo() } ?: listOf(),
        passengerId = passengerId,
        passengerName = passengerName,
        ticketNo = ticketNo
    )
}