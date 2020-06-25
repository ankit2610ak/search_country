package com.example.oodlestechnologies.ui.countryDetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.example.oodlestechnologies.R

class CountryDetailFragment : Fragment() {

    companion object {
        fun newInstance() = CountryDetailFragment()
    }

    private lateinit var viewModel: CountryDetailViewModel
    lateinit var alpha2code: String
    var flag: ImageView? = null
    var name: TextView? = null
    var capital: TextView? = null
    var region: TextView? = null
    var population: TextView? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.country_detail_fragment, container, false)
        alpha2code = arguments?.getString("alpha2code").toString()
        flag = view.findViewById(R.id.flag)
        name = view.findViewById(R.id.name)
        capital = view.findViewById(R.id.capital)
        region = view.findViewById(R.id.region)
        population = view.findViewById(R.id.population)
        viewModel = ViewModelProviders.of(this).get(CountryDetailViewModel::class.java)
        viewModel._livedata.observe(viewLifecycleOwner, Observer {
            name?.text = it.name
            capital?.text = it.capital
            region?.text = it.region
            population?.text = it.population.toString()
            Glide.with(this).load("it.flag").into(flag!!)
        })

        viewModel.getCountryDetails(alpha2code)

        return view

    }


}