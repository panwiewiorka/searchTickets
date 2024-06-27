package com.testproject1.searchtickets.data

import com.testproject1.searchtickets.Offers
import com.testproject1.searchtickets.TicketOffers
import com.testproject1.searchtickets.Tickets
import retrofit2.http.GET

interface TicketsApi {

    @GET("v3/928dea46-c271-42fb-9f41-9de896c63d23")
    suspend fun getConcertOffers(): Offers

    @GET("v3/1b8d3c78-ea10-47e0-8e8e-c4fd10e6709b")
    suspend fun getTicketOffers(): TicketOffers

    @GET("v3/b3f71148-a43b-401c-a323-5e37d4a688ed")
    suspend fun getTickets(): Tickets


    companion object {
        const val BASE_URL = "https://run.mocky.io/"
    }
}