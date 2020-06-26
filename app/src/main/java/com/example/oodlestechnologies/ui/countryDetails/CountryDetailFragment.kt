package com.example.oodlestechnologies.ui.countryDetails

import android.os.Bundle
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.oodlestechnologies.databinding.CountryDetailFragmentBinding
import com.example.oodlestechnologies.model.CountryHashMap

class CountryDetailFragment : Fragment() {

    companion object {
        fun newInstance() = CountryDetailFragment()
    }

    private lateinit var viewModel: CountryDetailViewModel
    private lateinit var binding: CountryDetailFragmentBinding
    lateinit var alpha2code: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = CountryDetailFragmentBinding.inflate(layoutInflater)
        val view = binding.root

        alpha2code = arguments?.getString("alpha2code").toString()
        viewModel = ViewModelProviders.of(this).get(CountryDetailViewModel::class.java)
        viewModel._livedata.observe(viewLifecycleOwner, Observer {
            binding.name.text = it.name
            binding.capital.text = it.capital
            binding.region.text = it.region
            binding.population.text = it.population.toString()
            binding.flag.text = Html.fromHtml(CountryHashMap.items[alpha2code]?.unicode)
        })

        viewModel.getCountryDetails(alpha2code)

        return view

    }


}