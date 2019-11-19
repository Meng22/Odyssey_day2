package com.example.odyssey_day2

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface Api3_Interface {
    //查看浴場狀況
    @GET("/api/Status")
    fun showBath3(): Call<ShowBath3Response>

    //進入浴場
    @POST("/api/enter")
    fun entryBath3(@Body entryRequest: EntryRequest): Call<Entry3Response>

    //查看泡澡人
    @GET("/api/userall")
    fun showPeople(): Call<ShowPeople3Response>


}