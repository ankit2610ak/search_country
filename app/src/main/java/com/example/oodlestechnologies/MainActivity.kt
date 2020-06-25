package com.example.oodlestechnologies

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.oodlestechnologies.`interface`.FragmentListener
import com.example.oodlestechnologies.ui.countryDetails.CountryDetailFragment
import com.example.oodlestechnologies.ui.main.MainFragment

class MainActivity : AppCompatActivity(), FragmentListener {

    companion object {
        fun newInstance() = MainActivity()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, MainFragment.newInstance())
                .commitNow()
        }

    }

    override fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, fragment)
            .commit()
    }
}