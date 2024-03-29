package com.company.baluairlines.core.data

import com.company.baluairlines.core.data.model.BookingReq
import com.company.baluairlines.core.data.model.FlightInfoReq
import com.company.baluairlines.core.data.model.FlightReq
import com.company.baluairlines.core.domain.TicketInfo
import retrofit2.Response
import retrofit2.http.*

/** интрфейс для сетевых запросов */
interface AirApi {

    /** функция запроса лучших цен полетов на неделю вперед от даты полета
     * @param date дата полета
     * @param departureAirport аэропорт вылета
     * @param arrivalAirport аэропорт прилета
     * @param maxTransits максимальное количество пересадок
     * @param fareCondition класс обслуживания
     * @param passengers количество пассажиров
     * @return массив цен
     * */
    @GET("/flights/best_price/?")
    suspend fun getFlightCosts(
        @Query("departure_date") date: Long,
        @Query("departure_airport") departureAirport: String,
        @Query("arrival_airport") arrivalAirport: String,
        @Query("max_transits") maxTransits: Int,
        @Query("fare_condition") fareCondition: String,
        @Query("num_of_passengers") passengers: Int,
    ): Response<ArrayList<Double?>>

    /** функция запроса полетов по параметрам
     * @param date дата полета
     * @param departureAirport аэропорт вылета
     * @param arrivalAirport аэропорт прилета
     * @param maxTransits максимальное количество пересадок
     * @param fareCondition класс обслуживания
     * @param passengers количество пассажиров
     * @return массив из полетов
     * */
    @GET("/flights/?&date_between=true")
    suspend fun getFlights(
        @Query("departure_date") date: Long,
        @Query("departure_airport") departureAirport: String,
        @Query("arrival_airport") arrivalAirport: String,
        @Query("max_transits") maxTransits: Int,
        @Query("fare_condition") fareCondition: String,
        @Query("num_of_passengers") passengers: Int,
    ): Response<ArrayList<FlightReq>>

    /** функция запроса статуса рейса по аэропортам вылета и прилета
     * @param departureAirport аэропорт вылета
     * @param arrivalAirport аэропорт прилета
     * @param date дата полета
     * @return массив из полетов
     * */
    @GET("/flights/path/{departure_airport}/{arrival_airport}")
    suspend fun flightStatusWithAirports(
        @Path("departure_airport") departureAirport: String,
        @Path("arrival_airport") arrivalAirport: String,
        @Query("departure_date") date: Long
    ): Response<FlightInfoReq>

    /** функция запроса полетов и конкретного аэропорта
     * @param departureAirport аэропорт вылета
     * @param date дата полета
     * @return массив из полетов
     * */
    @GET("/flights/table/{departure_airport}")
    suspend fun getFlightTable(
        @Path("departure_airport") departureAirport: String,
        @Query("departure_date") date: Long
    ): Response<List<FlightInfoReq>>


    /** функция запроса статуса рейса по номеру рейса
     * @param flightNumber номер рейса
     * @param date дата полета
     * @return информация о полете
     * */
    @GET("/flights/no/{flight_number}?")
    suspend fun flightStatusWithNumber(
        @Path("flight_number") flightNumber: String,
        @Query("departure_date") date: Long
    ): Response<FlightInfoReq>

    @POST(" /bookings/")
    suspend fun sendPersonalInfo(
        @Body ticketInfo: TicketInfo
    ): Response<BookingReq>

    @GET("/bookings/{book_ref}")
    suspend fun getBooking(
        @Path("book_ref") bookRef: String,
    ): Response<BookingReq>

}