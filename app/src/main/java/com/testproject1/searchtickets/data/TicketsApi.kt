package com.testproject1.searchtickets.data

import com.testproject1.searchtickets.Offers
import com.testproject1.searchtickets.TicketOffers
import com.testproject1.searchtickets.Tickets
import retrofit2.http.GET

interface TicketsApi {

    @GET("uc?id=1QLsEdrWMHEaqytEUTAv7abaGo5eeMO2j")
    suspend fun getConcertOffers(): Offers

    @GET("uc?id=144YpIHnM5GeSIvOZQ82IK_2vLy6RTaw8")
    suspend fun getConcertOffersRu(): Offers

    @GET("uc?id=1oj02MNzO-c5pR384FRMPGVnbo6C4Zx1x")
    suspend fun getTicketOffers(): TicketOffers

    @GET("uc?id=1XoCND3a1x6YaCqa563vN4WzeEMk7_Boz")
    suspend fun getTicketOffersRu(): TicketOffers

    @GET("uc?id=1hWlHN-8vUM8ktXa4ViB7BqDCBeIiFEYM")
    suspend fun getTickets(): Tickets

    @GET("uc?id=1EVAeh3M7WbMoK_MqRCW1PQYJAZZhMNMF")
    suspend fun getTicketsRu(): Tickets


    companion object {
        const val BASE_URL = "https://drive.google.com/"
    }
}