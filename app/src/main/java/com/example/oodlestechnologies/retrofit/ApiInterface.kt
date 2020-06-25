package com.example.oodlestechnologies.retrofit

import com.example.oodlestechnologies.model.CountryItem
import com.example.oodlestechnologies.model.CountryItemDetails
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiInterface {
    companion object {
        private const val ALL = "all"
    }

    @GET(ALL)
    fun getAll(): Call<ArrayList<CountryItem>>

    @GET("alpha/{IsoCode2}")
    fun getCountryDetails(
        @Path("IsoCode2") IsoCode2: String
    ): Call<CountryItemDetails>

}