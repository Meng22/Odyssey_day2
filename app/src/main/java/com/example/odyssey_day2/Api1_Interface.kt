package com.example.odyssey_day2

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface Api1_Interface {
    @GET("/api/PoolStatus")
    fun showBath1(): Call<ShowBath1Response>

    //進入浴場
    @POST("/api/PoolUser")
    fun entryBath1(@Body entryResponse: EntryRequest): Call<Entry1Response>
}