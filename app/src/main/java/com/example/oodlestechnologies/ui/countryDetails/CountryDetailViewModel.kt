package com.example.oodlestechnologies.ui.countryDetails

import ApiClient
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.oodlestechnologies.model.CountryItemDetails
import retrofit2.Call
import retrofit2.Response

class CountryDetailViewModel : ViewModel() {
    private var liveData: MutableLiveData<CountryItemDetails> = MutableLiveData()
    val _livedata: LiveData<CountryItemDetails>
        get() = liveData

    fun getCountryDetails(alpha2code: String) {
        val call: Call<CountryItemDetails> = ApiClient.getClient.getCountryDetails(alpha2code)
        call.enqueue(object : retrofit2.Callback<CountryItemDetails> {
            override fun onFailure(call: Call<CountryItemDetails>, t: Throwable) {
                Log.d("CountryDetailsViewModel", t.message.toString())
            }

            override fun onResponse(
                call: Call<CountryItemDetails>,
                response: Response<CountryItemDetails>
            ) {
                val countryDetails = response.body()
                liveData.postValue(countryDetails)
            }

        })
    }
}