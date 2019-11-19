package com.example.odyssey_day2

data class ShowBath1Response(
    val introduction: String,
    val peopleAmount: Int,
    val poolName: String,
    val upperLimit: Int
)

data class ShowBath3Response(
    val created_at: Any,
    val id: Int,
    val introduction: String,
    val limit: Int,
    val message: String,
    val name: String,
    val quantity: Int,
    val updated_at: Any
)


data class Entry1Response(
    val message: String
)
data class EntryRequest(
    val name: String
)

data class Entry3Response(
    val created_at: String,
    val drink: Any,
    val id: Int,
    val message: String,
    val name: String,
    val updated_at: String
)

data class ShowPeople3Response(
    val data: List<People>,
    val message: String
)

data class People(
    val created_at: String,
    val drink: Any,
    val id: Int,
    val name: String,
    val updated_at: String
)