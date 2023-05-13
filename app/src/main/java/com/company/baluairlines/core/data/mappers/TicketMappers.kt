package com.company.baluairlines.core.data.mappers

import com.company.baluairlines.core.data.database.FlightInfoEntity
import com.company.baluairlines.core.data.database.TicketEntity
import com.company.baluairlines.core.data.model.BookingReq
import com.company.baluairlines.core.data.model.TicketReq
import com.company.baluairlines.core.domain.Booking
import com.company.baluairlines.core.domain.FlightInfo
import com.company.baluairlines.core.domain.Ticket

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

fun Booking.toTicketEntityList(): List<TicketEntity> {
    val ticketEntityList = mutableListOf<TicketEntity>()
    this.tickets.forEach { ticket ->
        val ticketEntity = TicketEntity(
            ticketNumber = ticket.ticketNo,
            bookRef = this.bookRef,
            passengerName = ticket.passengerName
        )
        ticketEntityList.add(ticketEntity)
    }
    return ticketEntityList
}

fun Booking.toFlightInfoEntityList(flightInfoList: List<FlightInfo>): List<FlightInfoEntity> {
    val flightsEntity = mutableListOf<FlightInfoEntity>()
    this.tickets.forEach { ticket ->
        flightInfoList.forEach { flightInfo ->
            flightsEntity.add(flightInfo.toFlightInfoEntity(ticket.ticketNo))
        }
    }
    return flightsEntity
}

fun FlightInfo.toFlightInfoEntity(ticketNumber: String): FlightInfoEntity {
    return FlightInfoEntity(
        ticketNumber = ticketNumber,
        flightId = flightId,
        flightNo = flightNo,
        scheduledArrival = scheduledArrival,
        scheduledDeparture = scheduledDeparture,
        arrivalAirport = arrivalAirport,
        departureAirport = departureAirport,
        aircraftCode = aircraftCode
    )
}
