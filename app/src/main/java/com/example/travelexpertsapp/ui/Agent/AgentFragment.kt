package com.example.travelexpertsapp.ui.Agent

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import com.example.travelexpertsapp.R
import org.json.JSONArray

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
        val textView: TextView = root.findViewById(R.id.text_agent)

        val mQueue = Volley.newRequestQueue(activity?.applicationContext);

        val url = "http://10.0.0.183:8080/Workshop_7_war_exploded/api/agent"
        val request = JsonArrayRequest(Request.Method.GET, url, null,
            { response ->
                textView.text = response.getJSONObject(1).toString()
            },
            { error ->
                error.printStackTrace()
            }
        )

        mQueue.add(request)

//        agentViewModel.text.observe(viewLifecycleOwner, Observer {
//            textView.text = it
//        })

        return root
    }
}