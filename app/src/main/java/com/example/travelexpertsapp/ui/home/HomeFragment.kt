package com.example.travelexpertsapp.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.android.volley.Request
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import com.example.travelexpertsapp.R
import com.example.travelexpertsapp.data.Agent
import com.example.travelexpertsapp.data.Customer
import com.google.gson.Gson
import java.util.ArrayList

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
                ViewModelProvider(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        val lvCustomer: ListView = root.findViewById(R.id.lvCustomers)
        getCustomer(lvCustomer)
        return root
       // homeViewModel.text.observe(viewLifecycleOwner, Observer {


        }

        private fun getCustomer(list: ListView) {
            val context = activity?.applicationContext
            val mQueue = Volley.newRequestQueue(context)
            val url = "http://10.0.0.36:8080/Workshop_7_war_exploded/api/customer"
            var output = ArrayList<Customer>()

            val request = JsonArrayRequest(
                Request.Method.GET, url, null,
                { response ->

                    val gson = Gson()

                    for (i in 0 until response.length()) {
                        output.add(gson.fromJson(response.getJSONObject(i).toString(), Customer::class.java))
                    }

                    val listAdpt = context?.let { ArrayAdapter(it, android.R.layout.simple_list_item_1, output) }
                    list.adapter = listAdpt

                },
                { error ->
                    error.printStackTrace()
                }
            )
            mQueue.add(request)
    }//..onCreate
}//class HomeFragment