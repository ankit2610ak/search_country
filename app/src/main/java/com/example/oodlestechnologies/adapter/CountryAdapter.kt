package com.example.oodlestechnologies.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.oodlestechnologies.MainActivity
import com.example.oodlestechnologies.R
import com.example.oodlestechnologies.model.CountryItem
import com.example.oodlestechnologies.ui.countryDetails.CountryDetailFragment


class CountryAdapter(
    private val countryArrayList: ArrayList<CountryItem>
    , private val context: Context

) : RecyclerView.Adapter<CountryAdapter.CustomViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        return CustomViewHolder(

            LayoutInflater.from(context).inflate(R.layout.item_layout, parent, false)
        )

    }

    override fun getItemCount(): Int {
        return countryArrayList.size
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        val countryItem = countryArrayList[position]
//        holder.pic.text = Html.fromHtml("&#x1F1EE;&#x1F1F3")
        holder.name.text = countryItem.name
        holder.itemView.setOnClickListener {
            val fragment = CountryDetailFragment.newInstance()
            val bundle = Bundle()
            bundle.putString("alpha2code", countryItem.alpha2Code)
            fragment.arguments = bundle
            replaceFragment(fragment)
        }
    }

    private fun replaceFragment(fragment: CountryDetailFragment) {
        (context as MainActivity).replaceFragment(fragment)
    }


    class CustomViewHolder(itemLayoutView: View) : RecyclerView.ViewHolder(itemLayoutView) {
        var name: TextView = itemLayoutView.findViewById(R.id.heading)
        var pic: TextView = itemLayoutView.findViewById(R.id.icon)

    }

}
