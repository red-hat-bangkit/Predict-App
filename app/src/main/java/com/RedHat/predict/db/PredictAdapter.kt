package com.RedHat.predict.db

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.RedHat.predict.R
import com.RedHat.predict.databinding.ItemFloatBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import java.util.ArrayList

class PredictAdapter : RecyclerView.Adapter<PredictAdapter.WeatherViewHolder>() {

    private val mData = ArrayList<Data>()

    fun setData(items: ArrayList<Data>) {
        mData.clear()
        mData.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, position: Int): WeatherViewHolder {
        val mView = LayoutInflater.from(viewGroup.context).inflate(R.layout.item_float, viewGroup, false)
        return WeatherViewHolder(mView)
    }

    override fun onBindViewHolder(weatherViewHolder: WeatherViewHolder, position: Int) {
        weatherViewHolder.bind(mData[position])

//        weatherViewHolder.itemView.setOnClickListener() {
//
//            val activity = weatherViewHolder.itemView.context as Activity
//            val intent = Intent(activity , Detailgithub::class.java).apply {
//                putExtra(Detailgithub.ARG_section_username, mData[position])
//
//            }
//            activity.startActivity(intent)
//        }
    }

    override fun getItemCount(): Int = mData.size

    inner class WeatherViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemFloatBinding.bind(itemView)
        fun bind(data: Data) {
            with(itemView){
                Glide.with(itemView.context)
                    .load(R.drawable.samplepict)
                    .apply(RequestOptions().override(200, 200))
                    .into(binding.imageItem)
                binding.dtIBencana.text = data.bencana
                binding.dtIWilayah.text = data.locationname
                binding.dtIWaktu.text = data.waktu
//                itemView.setOnClickListener { Toast.makeText(itemView.context, "Kamu memilih ${gith.username}", Toast.LENGTH_SHORT).show()
//
//
//                }
            }
        }

    }
}