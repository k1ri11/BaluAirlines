package com.company.baluairlines.core.utils

import android.content.Context
import com.company.baluairlines.core.data.mappers.toFlightInfo
import com.company.baluairlines.core.data.model.FlightInfoReq
import com.company.baluairlines.core.domain.FlightInfo
import com.company.myapplication.R
import retrofit2.Response

//todo подумать как вынести контекст
fun handleNetworkResponse(response: Response<FlightInfoReq>, context: Context): Resource<FlightInfo> {
    if (response.isSuccessful) {
        response.body()?.let { body ->
            return Resource.Success(body.toFlightInfo())
        }
    }
    return when (response.code()) {
        400 -> Resource.Error(context.resources.getString(R.string.incorrect_request))
        401 -> Resource.Error(context.resources.getString(R.string.incorrect_authorization))
        404 -> Resource.Error(context.resources.getString(R.string.element_not_found))
        else -> Resource.Error(context.resources.getString(R.string.server_error))
    }
}