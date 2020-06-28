package com.example.oodlestechnologies

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.oodlestechnologies.`interface`.FragmentListener
import com.example.oodlestechnologies.model.CountryHashMap
import com.example.oodlestechnologies.model.JSONDataClass
import com.example.oodlestechnologies.ui.main.MainFragment
import com.example.oodlestechnologies.utils.Utils
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class MainActivity : AppCompatActivity(), FragmentListener {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.container, MainFragment.newInstance())
                .commitNow()
        }

        val jsonFileString = Utils.getJsonDataFromAsset(this, "countryflag.json")

        val gson = Gson()
        val listCountyType = object : TypeToken<List<JSONDataClass>>() {}.type

        val listOfJSonData: List<JSONDataClass> = gson.fromJson(jsonFileString, listCountyType)

        listOfJSonData.forEach { i ->
            CountryHashMap.items[i.code] = i
        }

    }


    override fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .add(R.id.container,fragment)
            .addToBackStack("2nd fragment")
            .commit()
    }
}