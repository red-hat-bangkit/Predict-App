package com.RedHat.predict

import android.R
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.RedHat.predict.db.Data
import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.AsyncHttpResponseHandler
import org.json.JSONArray
import org.json.JSONObject
import cz.msebera.android.httpclient.Header
class MainViewModel : ViewModel() {
    companion object {
        private val TAG = MainActivity::class.java.simpleName
    }

    val listGith = MutableLiveData<ArrayList<Data>>()



    fun getGith(): LiveData<ArrayList<Data>> {
        return listGith
    }



     fun getListQuotes(locationname : String,futuredays :String, after_date:String) {
//        binding.progressBar.visibility = View.VISIBLE
        val client = AsyncHttpClient()
        val url = "https://predict-bencana.ardenov.com/v1/predictions?bencana_name=banjir&location_name=$locationname&future_days=$futuredays&after_date=$after_date"
        client.get(url, object : AsyncHttpResponseHandler() {
            override fun onSuccess(statusCode: Int, headers: Array<Header>, responseBody: ByteArray) {
                // Jika koneksi berhasil
//                binding.progressBar.visibility = View.INVISIBLE
                // Parsing JSON
                val listQuote = ArrayList<Data>()
                val result = String(responseBody)
                Log.d(TAG, result)
                try {
                    val jsonArray = JSONArray(result)
                    for (i in 0 until jsonArray.length()) {
                        val jsonObject = jsonArray.getJSONObject(i)
                        val bencana = jsonObject.getString("bencana")
                        val waktu = jsonObject.getString("time")
                        val lokasi = jsonObject.getString("location")
                        val reas = jsonObject.getString("reason")
//                        Log.d(TAG, lokasi.toString())
                        val time = lokasi.split(":",",").toTypedArray()
                        Log.d(TAG, time[5].toString())
                        val user = Data()
//                        val jsonArraylok = JSONArray(lokasi)
//                        for (i in 0 until jsonArraylok.length()) {
//                            val namalokasi = jsonObject.getString("name")
//                            user.locationname = namalokasi
//                            Log.d(TAG, user.locationname.toString())
//                        }
                        user.locationname = time[1]
                        user.bencana=bencana
                        user.waktu=waktu
                        user.locationconfidience=reas
                        user.locationlatlong = time[5]
//                        user.locationname = lokasi.toString()

                        listQuote.add(user)
                    }
                    listGith.postValue(listQuote)

                } catch (e: Exception) {
//                    Toast.makeText(this@listquotes, e.message, Toast.LENGTH_SHORT).show()
                    e.printStackTrace()
                }

            }
            override fun onFailure(statusCode: Int, headers: Array<Header>, responseBody: ByteArray, error: Throwable) {
                // Jika koneksi gagal
//                binding.progressBar.visibility = View.INVISIBLE
                val errorMessage = when (statusCode) {
                    401 -> "$statusCode : Bad Request"
                    403 -> "$statusCode : Forbidden"
                    404 -> "$statusCode : Not Found"
                    else -> "$statusCode : ${error.message}"
                }
//                Toast.makeText(this@listquotes, errorMessage, Toast.LENGTH_SHORT).show()
            }
        })
    }
}