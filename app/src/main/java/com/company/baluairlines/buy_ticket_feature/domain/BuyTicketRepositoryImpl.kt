package com.company.baluairlines.buy_ticket_feature.domain

import android.content.Context
import com.company.baluairlines.buy_ticket_feature.data.BuyTicketRepository
import com.company.baluairlines.core.data.AirApi
import com.company.baluairlines.core.data.mappers.toFlight
import com.company.baluairlines.core.di.ApplicationScope
import com.company.baluairlines.core.domain.Flight
import com.company.baluairlines.core.utils.Resource
import com.company.myapplication.R
import javax.inject.Inject

@ApplicationScope
class BuyTicketRepositoryImpl @Inject constructor(
    private val api: AirApi,
    private val context: Context
): BuyTicketRepository {
    override suspend fun getFlights(): Resource<List<Flight>> {
        val response = api.getFlights()
        if (response.isSuccessful){
            val result = response.body()!!.map { it.toFlight() }
            return Resource.Success(result)
        }
        return when (response.code()) {
            400 -> Resource.Error(context.resources.getString(R.string.incorrect_request))
            401 -> Resource.Error(context.resources.getString(R.string.incorrect_authorization))
            404 -> Resource.Error(context.resources.getString(R.string.element_not_found))
            else -> Resource.Error(context.resources.getString(R.string.server_error))
        }

    }
}