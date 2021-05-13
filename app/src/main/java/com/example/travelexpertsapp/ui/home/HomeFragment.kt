package com.example.travelexpertsapp.ui.home

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.replace
import androidx.lifecycle.ViewModelProvider
import com.android.volley.Request
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import com.example.travelexpertsapp.R
import com.example.travelexpertsapp.R.*
import com.example.travelexpertsapp.data.Customer
import com.example.travelexpertsapp.data.Package
import com.google.gson.Gson
import java.util.*


class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel

    @SuppressLint("ResourceType")
    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
                ViewModelProvider(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(layout.fragment_home, container, false)
        val lvCustomer: ListView = root.findViewById(R.id.lvCustomers)
        val tvName = root.findViewById<TextView>(R.id.tvName)
        val tvAddress = root.findViewById<TextView>(R.id.tvAddress)
        val tvContact = root.findViewById<TextView>(R.id.tvContact)
        lvCustomer.setOnItemClickListener(AdapterView.OnItemClickListener { parent, view, position, id ->
            val c = lvCustomer.getAdapter().getItem(position) as Customer
            //tvPackages.setText(c.packageDetail())
            tvName.setText(c.custFirstName + " " + c.custLastName);
            tvAddress.setText("Address: " + c.custAddress + "  City: " + c.custCity + "  Prov: " + c.custProv + "  Postal Code:" + c.custPostal );
            tvContact.setText("Phone Number: " + c.custBusPhone+"/"+c.custHomePhone + "  Email: " + c.custEmail );

        })

        getCustomer(lvCustomer)
        return root


        }


    private fun getCustomer(list: ListView) {
            val context = activity?.applicationContext
            val mQueue = Volley.newRequestQueue(context)
            val url = "http://192.168.1.70:8080/Workshop_7_war_exploded/api/customer"
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