package com.testproject1.searchtickets.data

import com.testproject1.searchtickets.Offers
import com.testproject1.searchtickets.TicketOffers
import com.testproject1.searchtickets.Tickets
import retrofit2.http.GET

interface TicketsApi {

    @GET("v3/ad9a46ba-276c-4a81-88a6-c068e51cce3a")
    suspend fun getConcertOffers(): Offers

    @GET("v3/38b5205d-1a3d-4c2f-9d77-2f9d1ef01a4a")
    suspend fun getTicketOffers(): TicketOffers

    @GET("v3/c0464573-5a13-45c9-89f8-717436748b69")
    suspend fun getTickets(): Tickets


    companion object {
        const val BASE_URL = "https://run.mocky.io/"
    }
}