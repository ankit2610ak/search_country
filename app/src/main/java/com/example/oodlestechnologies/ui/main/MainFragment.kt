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
import androidx.recyclerview.widget.RecyclerView
import com.example.oodlestechnologies.R
import com.example.oodlestechnologies.adapter.CountryAdapter
import com.example.oodlestechnologies.model.CountryItem

@Suppress("DEPRECATION")
class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: CountryAdapter
    private var countryList: ArrayList<CountryItem> = ArrayList()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.main_fragment, container, false)
        recyclerView = view.findViewById(R.id.recycler_view)
        recyclerView.layoutManager =
            GridLayoutManager(this.requireContext(), 3)

        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        viewModel._livedata.observe(viewLifecycleOwner, Observer {
            countryList.clear()
            countryList.addAll(it)
            adapter.notifyDataSetChanged()
        })
        adapter = CountryAdapter(countryList, this.requireContext())

        viewModel.getAllCountries()
        recyclerView.adapter = adapter

        return view

    }

}