package com.frand.crud.network

import com.frand.crud.model.*
import com.frand.crud.model.ResponseProducts
import retrofit2.Call
import retrofit2.http.GET

interface ApiOne {
    @GET("products")
    fun getProducts(): Call<ResponseProducts>

}