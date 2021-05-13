package com.example.travelexpertsapp.ui.Agent

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.view.get
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.android.volley.Request
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import com.example.travelexpertsapp.R
import com.example.travelexpertsapp.data.Agent
import com.example.travelexpertsapp.data.Package
import com.google.gson.Gson
import java.util.*

class AgentFragment : Fragment() {

    private lateinit var agentViewModel: AgentViewModel


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        agentViewModel =
                ViewModelProvider(this).get(AgentViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_agent, container, false)
        val agentListView = root.findViewById<ListView>(R.id.agentListView)
        val AgtPosition = root.findViewById<TextView>(R.id.AgtPosition)
        val AgtEmail = root.findViewById<TextView>(R.id.AgtEmail)
        val AgtBusPhone = root.findViewById<TextView>(R.id.AgtBusPhone)
        agentListView.setOnItemClickListener(AdapterView.OnItemClickListener { parent, view, position, id ->
            val c = agentListView.getAdapter().getItem(position) as Agent
            //tvPackages.setText(c.packageDetail())
            AgtPosition.setText(c.agtFirstName +": "+ c.agtPosition);
            AgtEmail.setText("Email: "+ c.agtEmail);
            AgtBusPhone.setText("Phone Number: " + c.agtBusPhone);
        })

        getAgents(agentListView)
        return root
    }

    private fun getAgents(list: ListView) {
        val context = activity?.applicationContext
        val mQueue = Volley.newRequestQueue(context)
        val url = "http://192.168.1.70:8080/Workshop_7_war_exploded/api/agent"
        var output = ArrayList<Agent>()

        val request = JsonArrayRequest(Request.Method.GET, url, null,
            { response ->

                val gson = Gson()

                for (i in 0 until response.length()) {
                    output.add(gson.fromJson(response.getJSONObject(i).toString(), Agent::class.java))
                }

                val listAdpt = context?.let { ArrayAdapter(it, android.R.layout.simple_list_item_1, output) }
                list.adapter = listAdpt

            },
            { error ->
                error.printStackTrace()
            }
        )
        mQueue.add(request)
    }

}