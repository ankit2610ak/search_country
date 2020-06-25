package com.example.oodlestechnologies.ui.main

import ApiClient
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.oodlestechnologies.model.CountryItem
import retrofit2.Call
import retrofit2.Response

class MainViewModel : ViewModel() {
    private var liveData: MutableLiveData<ArrayList<CountryItem>> = MutableLiveData()
    val _livedata: LiveData<ArrayList<CountryItem>>
        get() = liveData

    fun getAllCountries() {
        val call: Call<ArrayList<CountryItem>> = ApiClient.getClient.getAll()
        call.enqueue(object : retrofit2.Callback<ArrayList<CountryItem>> {
            override fun onFailure(call: Call<ArrayList<CountryItem>>, t: Throwable) {
                Log.d("MainViewModel", t.message.toString())
            }

            override fun onResponse(
                call: Call<ArrayList<CountryItem>>,
                response: Response<ArrayList<CountryItem>>
            ) {
                val countryList = response.body()
                liveData.postValue(countryList)
            }

        })
    }

}