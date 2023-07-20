package com.example.recyclaearviewpagination.networking


import com.example.recyclaearviewpagination.model.UserData
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("api/users")
    fun getUsers(
        @Query("page") page: Int = 1
    ): Call<UserData>

    @GET("api/users")
    suspend fun getPaging3Users(
        @Query("page") page: Int = 1
    ): Response<UserData>
}