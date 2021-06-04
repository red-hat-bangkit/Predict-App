package com.RedHat.predict

import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import com.RedHat.predict.databinding.ActivityMainBinding
import com.RedHat.predict.databinding.ActivitySearchBinding
import com.RedHat.predict.db.Data
import java.text.SimpleDateFormat
import java.util.*

class search : AppCompatActivity() {
    companion object {
        private val TAG = search::class.java.simpleName

        const val EXTRA_NOTE = "extra_note"
    }
    var button_date: Button? = null
    var cal = Calendar.getInstance()
    private lateinit var binding: ActivitySearchBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)
       binding.btnSubmit.setOnClickListener {

            registeruser()

        }
        button_date = this.binding.buttonDate1
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
//                    Toast.makeText(this@search,
//                        getString(R.string.Lokasi) + " " +
//                                "" + loc[position], Toast.LENGTH_SHORT).show()
                    intent.putExtra(EXTRA_NOTE, loc[position])
                }

                override fun onNothingSelected(parent: AdapterView<*>) {
                    // write code to perform some action
                }
            }
        }
        val dateSetListener = object : DatePickerDialog.OnDateSetListener {
            override fun onDateSet(view: DatePicker, year: Int, monthOfYear: Int,
                                   dayOfMonth: Int) {
                cal.set(Calendar.YEAR, year)
                cal.set(Calendar.MONTH, monthOfYear)
                cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                updateDateInView()
            }
        }

        button_date!!.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View) {
                DatePickerDialog(this@search,
                    dateSetListener,
                    // set DatePickerDialog to point to today's date when it loads up
                    cal.get(Calendar.YEAR),
                    cal.get(Calendar.MONTH),
                    cal.get(Calendar.DAY_OF_MONTH)).show()
            }

        })

    }
    private fun updateDateInView() {
        val myFormat = "yyyy-MM-dd" // mention the format you need
        val sdf = SimpleDateFormat(myFormat, Locale.US)
        binding.edtPassword.text = sdf.format(cal.getTime())
        Toast.makeText(this@search,
                        sdf.format(cal.getTime()).toString(), Toast.LENGTH_SHORT).show()
    }
    private fun registeruser(){
//        val nama = binding.edtNama.text.toString()
        val user = intent.getStringExtra(EXTRA_NOTE)
        val email = binding.edtEmail.text.toString()
        val password = binding.edtPassword.text.toString()


        val test = user?.let { Data("", it,"","","","","","",email,password) }

        when {
            email.isEmpty() -> {
                binding.edtEmail.error = "Masih kosong"
            }
            password.isEmpty() -> {
                binding.edtPassword.error = "Masih kosong"
            }
            else -> {
                val intent = Intent(this@search , MainActivity::class.java).apply {
                    putExtra(MainActivity.ARG_section_username, test)

                }
                startActivity(intent)
            }
        }



    }
}