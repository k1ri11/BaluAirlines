package com.company.baluairlines.core.utils

import android.content.Context
import com.company.baluairlines.core.data.mappers.toFlight
import com.company.baluairlines.core.data.mappers.toFlightInfo
import com.company.baluairlines.core.data.model.FlightInfoReq
import com.company.baluairlines.core.data.model.FlightReq
import com.company.baluairlines.core.domain.BookingReq
import com.company.baluairlines.core.domain.Flight
import com.company.baluairlines.core.domain.FlightInfo
import com.company.myapplication.R
import retrofit2.Response

//todo подумать как вынести контекст
/**
 * метод для обработки ответа сервера и оборачивание его в [Resource] в случае ошибки обретка в [Resource.Error] с описанием кода ошибки
 * @param response ответ от сервера
 * @param context контекст
 * @return [Resource.Success] или [Resource.Error]
 */
fun handleListNetworkResponse(response: Response<List<FlightInfoReq>>, context: Context): Resource<List<FlightInfo>> {
    if (response.isSuccessful) {
        response.body()?.let { body ->
            return Resource.Success(body.map { it.toFlightInfo() })
        }
    }
    return when (response.code()) {
        400 -> Resource.Error(context.resources.getString(R.string.incorrect_request))
        401 -> Resource.Error(context.resources.getString(R.string.incorrect_authorization))
        404 -> Resource.Error(context.resources.getString(R.string.element_not_found))
        else -> Resource.Error(context.resources.getString(R.string.server_error))
    }
}

/**
 * метод для обработки ответа сервера и оборачивание его в [Resource] в случае ошибки обретка в [Resource.Error] с описанием кода ошибки
 * @param response ответ от сервера
 * @param context контекст
 * @return [Resource.Success] или [Resource.Error]
 */
fun handleSingleNetworkResponse(response: Response<FlightInfoReq>, context: Context): Resource<List<FlightInfo>> {
    if (response.isSuccessful) {
        response.body()?.let { body ->
            return Resource.Success(listOf(body.toFlightInfo()))
        }
    }
    return when (response.code()) {
        400 -> Resource.Error(context.resources.getString(R.string.incorrect_request))
        401 -> Resource.Error(context.resources.getString(R.string.incorrect_authorization))
        404 -> Resource.Error(context.resources.getString(R.string.element_not_found))
        else -> Resource.Error(context.resources.getString(R.string.server_error))
    }
}

/**
 * метод для обработки ответа сервера и оборачивание его в [Resource] в случае ошибки обретка в [Resource.Error] с описанием кода ошибки
 * @param response ответ от сервера
 * @param context контекст
 * @return [Resource.Success] или [Resource.Error]
 */
fun handleFlightListNetworkResponse(response: Response<ArrayList<FlightReq>>, context: Context): Resource<List<Flight>> {
    if (response.isSuccessful) {
        response.body()?.let { body ->
            return Resource.Success(body.map { it.toFlight() })
        }
    }
    return when (response.code()) {
        400 -> Resource.Error(context.resources.getString(R.string.incorrect_request))
        401 -> Resource.Error(context.resources.getString(R.string.incorrect_authorization))
        404 -> Resource.Error(context.resources.getString(R.string.element_not_found))
        else -> Resource.Error(context.resources.getString(R.string.server_error))
    }
}

fun handleBookingResponse(response: Response<BookingReq>, context: Context): Resource<BookingReq> {
    if (response.isSuccessful) {
        response.body()?.let { body ->
            return Resource.Success(body)
        }
    }
    return when (response.code()) {
        400 -> Resource.Error(context.resources.getString(R.string.incorrect_request))
        401 -> Resource.Error(context.resources.getString(R.string.incorrect_authorization))
        404 -> Resource.Error(context.resources.getString(R.string.element_not_found))
        else -> Resource.Error(context.resources.getString(R.string.server_error))
    }
}
