package com.company.baluairlines.buy_ticket_feature.data

import com.company.baluairlines.core.domain.Flight
import com.company.baluairlines.core.utils.Resource

interface BuyTicketRepository {

    suspend fun getFlights(): Resource<List<Flight>>
}