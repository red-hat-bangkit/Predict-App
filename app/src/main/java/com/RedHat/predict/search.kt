package com.RedHat.predict

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.RedHat.predict.databinding.ActivityMainBinding
import com.RedHat.predict.databinding.ActivitySearchBinding
import com.RedHat.predict.db.Data

class search : AppCompatActivity() {
    companion object {
        private val TAG = search::class.java.simpleName
    }
    private lateinit var binding: ActivitySearchBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)
       binding.btnSubmit.setOnClickListener {

            registeruser()

        }

    }

    private fun registeruser(){
        val nama = binding.edtNama.text.toString()

        val email = binding.edtEmail.text.toString()
        val password = binding.edtPassword.text.toString()
        val test =  Data("",nama,"","","","","","",email,password)

        val intent = Intent(this@search , MainActivity::class.java).apply {
            putExtra(MainActivity.ARG_section_username, test)

        }
        startActivity(intent)


    }
}