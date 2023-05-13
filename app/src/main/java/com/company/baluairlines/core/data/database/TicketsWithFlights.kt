package com.company.baluairlines.core.data.database

import androidx.room.Embedded
import androidx.room.Relation

data class TicketsWithFlights(
    @Embedded
    val ticket: TicketEntity,
    @Relation(
        parentColumn = "ticket_number",
        entityColumn = "ticket_number"
    )
    val flights: List<FlightInfoEntity>,
    )