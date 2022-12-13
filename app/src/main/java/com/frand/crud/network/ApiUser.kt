package com.frand.crud.network

import com.frand.crud.model.*
import com.frand.crud.model.ResponseGet
import retrofit2.Call
import retrofit2.http.GET

interface ApiUser {
    @GET("/api/users?page=2")
    fun getData(): Call<ResponseGet>

}