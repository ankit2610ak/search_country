@file:Suppress("DEPRECATION")

package com.example.oodlestechnologies.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.example.oodlestechnologies.adapter.CountryAdapter
import com.example.oodlestechnologies.databinding.MainFragmentBinding
import com.example.oodlestechnologies.model.CountryItem
import com.example.oodlestechnologies.model.JSONDataClass
import com.example.oodlestechnologies.utils.Utils
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

@Suppress("DEPRECATION")
class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel
    private lateinit var binding: MainFragmentBinding
    private lateinit var adapter: CountryAdapter
    private var countryList: ArrayList<CountryItem> = ArrayList()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = MainFragmentBinding.inflate(layoutInflater)
        val view = binding.root
        binding.recyclerView.layoutManager =
            GridLayoutManager(this.requireContext(), 3)

        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        viewModel._livedata.observe(viewLifecycleOwner, Observer {
            countryList.clear()
            countryList.addAll(it)
            adapter.notifyDataSetChanged()
        })
        adapter = CountryAdapter(countryList, this.requireContext())

        viewModel.getAllCountries()
        binding.recyclerView.adapter = adapter

        return view

    }

}