@file:Suppress("DEPRECATION")

package com.example.oodlestechnologies.ui.main

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.example.oodlestechnologies.adapter.CountryAdapter
import com.example.oodlestechnologies.databinding.MainFragmentBinding

@Suppress("DEPRECATION")
class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel
    private lateinit var binding: MainFragmentBinding
    private lateinit var adapter: CountryAdapter

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
            adapter.updateList(it)
            binding.progressBar.visibility = View.GONE
        })

        adapter = CountryAdapter(ArrayList(), this.requireContext())
        viewModel.getAllCountries()
        binding.recyclerView.adapter = adapter

        binding.searchEdittext.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(
                s: CharSequence,
                start: Int,
                count: Int,
                after: Int
            ) {
                Log.d("MainFragment", s.toString())
            }

            override fun onTextChanged(
                s: CharSequence,
                start: Int,
                before: Int,
                count: Int
            ) {
                viewModel.filterText(s.toString())
            }

            override fun afterTextChanged(s: Editable) {

            }
        })

        return view

    }


}