package com.example.travelexpertsapp.ui.notifications

import android.graphics.Color
import android.media.Image
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView.OnItemClickListener
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.ListView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.android.volley.Request
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import com.example.travelexpertsapp.R
import com.example.travelexpertsapp.data.Customer
import com.example.travelexpertsapp.data.Package
import com.google.gson.Gson
import java.util.*


class NotificationsFragment : Fragment() {

    private lateinit var notificationsViewModel: NotificationsViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        notificationsViewModel =
                ViewModelProvider(this).get(NotificationsViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_notifications, container, false)
        val lvPackages= root.findViewById<ListView>(R.id.lvPackages)
        val pkgName = root.findViewById<TextView>(R.id.pkgName)
        val pkgStartDate = root.findViewById<TextView>(R.id.pkgStartDate)
        val pkgEndDate = root.findViewById<TextView>(R.id.pkgEndDate)
        val pkgDesc = root.findViewById<TextView>(R.id.pkgDesc)
        val pkgBasePrice = root.findViewById<TextView>(R.id.pkgBasePrice)
        val pkgAgencyCommission = root.findViewById<TextView>(R.id.pkgAgencyCommission)
        val img = root.findViewById<ImageView>(R.id.imageView2)

        lvPackages.setOnItemClickListener(OnItemClickListener { parent, view, position, id ->
            val c = lvPackages.getAdapter().getItem(position) as Package
            //tvPackages.setText(c.packageDetail())
            pkgName.setText(c.pkgName);
            pkgStartDate.setText("Start Date: " + c.pkgStartDate);
            pkgEndDate.setText("End Date: " + c.pkgEndDate);
            pkgDesc.setText(c.pkgDesc);
            pkgBasePrice.setText("Base Price: " + "$"+c.pkgBasePrice);
            pkgAgencyCommission.setText("Commission: $" + c.pkgAgencyCommission);
            if(c.packageId == 1)
            {
                img.setImageResource(R.drawable.one);
                pkgName.setTextColor(Color.WHITE);
                pkgStartDate.setTextColor(Color.WHITE);
                pkgEndDate.setTextColor(Color.WHITE);
                pkgDesc.setTextColor(Color.WHITE);
                pkgBasePrice.setTextColor(Color.WHITE);
                pkgAgencyCommission.setTextColor(Color.WHITE);
            }
           else if(c.packageId == 2)
            {
                img.setImageResource(R.drawable.two);
                pkgName.setTextColor(Color.WHITE);
                pkgStartDate.setTextColor(Color.WHITE);
                pkgEndDate.setTextColor(Color.WHITE);
                pkgDesc.setTextColor(Color.WHITE);
                pkgBasePrice.setTextColor(Color.WHITE);
                pkgAgencyCommission.setTextColor(Color.WHITE);
            }
            else if(c.packageId == 3)
            {
                img.setImageResource(R.drawable.three);
                    pkgName.setTextColor(Color.BLACK);
                    pkgStartDate.setTextColor(Color.BLACK);
                    pkgEndDate.setTextColor(Color.BLACK);
                    pkgDesc.setTextColor(Color.WHITE);
                    pkgBasePrice.setTextColor(Color.BLACK);
                    pkgAgencyCommission.setTextColor(Color.BLACK);

            }
            else
            {
                img.setImageResource(R.drawable.four);
                pkgName.setTextColor(Color.WHITE);
                pkgStartDate.setTextColor(Color.WHITE);
                pkgEndDate.setTextColor(Color.WHITE);
                pkgDesc.setTextColor(Color.WHITE);
                pkgBasePrice.setTextColor(Color.WHITE);
                pkgAgencyCommission.setTextColor(Color.WHITE);
            }
        })


        getPackages(lvPackages)
        return root
    }



    private fun getPackages(list: ListView) {
        val context = activity?.applicationContext
        val mQueue = Volley.newRequestQueue(context)
        val url = "http://192.168.1.70:8080/Workshop_7_war_exploded/api/package/get-all-packages"
        var output = ArrayList<Package>()

        val request = JsonArrayRequest(
            Request.Method.GET, url, null,
            { response ->

                val gson = Gson()

                for (i in 0 until response.length()) {
                    output.add(
                        gson.fromJson(
                            response.getJSONObject(i).toString(),
                            Package::class.java
                        )
                    )
                }

                val listAdpt = context?.let {
                    ArrayAdapter(
                        it,
                        android.R.layout.simple_list_item_1,
                        output
                    )
                }
                list.adapter = listAdpt

            },
            { error ->
                error.printStackTrace()
            }
        )
        mQueue.add(request)
    }//..getPackages

}//class NotificationsFragment