package com.RedHat.predict

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import com.RedHat.predict.databinding.ActivityMainBinding
import com.RedHat.predict.databinding.ActivitySearchBinding
import com.RedHat.predict.db.Data

class search : AppCompatActivity() {
    companion object {
        private val TAG = search::class.java.simpleName

        const val EXTRA_NOTE = "extra_note"
    }
    private lateinit var binding: ActivitySearchBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)
       binding.btnSubmit.setOnClickListener {

            registeruser()

        }

        val loc = resources.getStringArray(R.array.location)
        val spinner = binding.edtNama
        if (spinner != null) {
            val adapter = ArrayAdapter(this,
                android.R.layout.simple_spinner_item, loc)
            spinner.adapter = adapter

            spinner.onItemSelectedListener = object :
                AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>,
                                            view: View, position: Int, id: Long) {
                    Toast.makeText(this@search,
                        getString(R.string.Lokasi) + " " +
                                "" + loc[position], Toast.LENGTH_SHORT).show()
                    intent.putExtra(EXTRA_NOTE, loc[position])
                }

                override fun onNothingSelected(parent: AdapterView<*>) {
                    // write code to perform some action
                }
            }
        }
    }

    private fun registeruser(){
//        val nama = binding.edtNama.text.toString()
        val user = intent.getStringExtra(EXTRA_NOTE)
        val email = binding.edtEmail.text.toString()
        val password = binding.edtPassword.text.toString()
        val test = user?.let { Data("", it,"","","","","","",email,password) }

        val intent = Intent(this@search , MainActivity::class.java).apply {
            putExtra(MainActivity.ARG_section_username, test)

        }
        startActivity(intent)


    }
}