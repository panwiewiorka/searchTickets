package com.testproject1.searchtickets.data

import com.testproject1.searchtickets.Offers
import com.testproject1.searchtickets.TicketOffers
import com.testproject1.searchtickets.Tickets
import retrofit2.http.GET

interface TicketsApi {

    @GET("uc?id=1o1nX3uFISrG1gR-jr_03Qlu4_KEZWhav")
    suspend fun getConcertOffers(): Offers

    @GET("uc?id=13WhZ5ahHBwMiHRXxWPq-bYlRVRwAujta")
    suspend fun getTicketOffers(): TicketOffers

    @GET("uc?id=1HogOsz4hWkRwco4kud3isZHFQLUAwNBA")
    suspend fun getTickets(): Tickets


    companion object {
        const val BASE_URL = "https://drive.google.com/"
    }
}