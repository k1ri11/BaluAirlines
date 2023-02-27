package com.company.baluairlines.buy_ticket_feature.data

import com.company.baluairlines.core.utils.Resource
import com.company.baluairlines.core.domain.Flight

interface Repository {

    suspend fun getFlights(): Resource<List<Flight>>
}