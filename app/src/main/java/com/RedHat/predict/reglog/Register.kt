package com.RedHat.predict.reglog

import android.R
import android.app.Activity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.RedHat.predict.databinding.ActivityRegisterBinding
import com.RedHat.predict.db.users
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference

import com.google.firebase.database.FirebaseDatabase

class Register : AppCompatActivity() {
    private lateinit var RegisterBinding: ActivityRegisterBinding
     private lateinit var mAuth : FirebaseAuth
    private lateinit var refUsers : DatabaseReference
    private var  firebaseuserid: String =""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        RegisterBinding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(RegisterBinding.root)
//        val languages = resources.getStringArray(R.array)
//
//        // access the spinner
////        val spinner = findViewById<Spinner>(R.id.spinner)
//        if (RegisterBinding.edtLokasi != null) {
//            val adapter = ArrayAdapter(this,
//                R.layout.simple_spinner_item, languages)
//            RegisterBinding.edtLokasi .adapter = adapter
//
//            RegisterBinding.edtLokasi.onItemSelectedListener = object :
//                AdapterView.OnItemSelectedListener {
//                override fun onItemSelected(parent: AdapterView<*>,
//                                            view: View, position: Int, id: Long) {
//                    Toast.makeText(this@Register,
//                        "" + languages[position], Toast.LENGTH_SHORT).show()
//                }
//
//                override fun onNothingSelected(parent: AdapterView<*>) {
//                    // write code to perform some action
//                }
//            }
//        }


//        RegisterBinding.btnSubmit.setOnClickListener { it ->
//            //Mendapatkan UserID dari pengguna yang Terautentikasi
//            val getUserID = ref.push().key.toString()
//
//            val database = FirebaseDatabase.getInstance()
//            val getReference = database.reference
//
//
//            val nama = RegisterBinding.edtNama.text.toString()
//            val email = RegisterBinding.edtEmail.text.toString()
//            val password = RegisterBinding.edtPassword.text.toString()
//            if (nama.isEmpty() && email.isEmpty() && password.isEmpty())
//                toast("Data tidak boleh ada yang kosong")
//            else if (getUserID != null)
//                getReference.child("users").child(getUserID).child("Mahasiswa").push()
//                    .setValue(users(nama, email, password))
//                    .addOnSuccessListener {
//                        RegisterBinding.edtNama.setText("")
//                        RegisterBinding.edtEmail.setText("")
//                        RegisterBinding.edtPassword.setText("")
//                        toast("Data tersimpan")
//                    }
//        }
        mAuth = FirebaseAuth.getInstance()

        RegisterBinding.btnSubmit.setOnClickListener {

            registeruser()
//            savedata()
        }
    }


    private fun registeruser(){
        val nama = RegisterBinding.edtNama.text.toString()

        val email = RegisterBinding.edtEmail.text.toString()
        val password = RegisterBinding.edtPassword.text.toString()
        mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener{
            task->
            if(task.isSuccessful)
            {
                    Toast.makeText(this, "Successs", Toast.LENGTH_SHORT).show()
                    firebaseuserid = mAuth.currentUser!!.uid
                    refUsers = FirebaseDatabase.getInstance().reference.child("pengguna").child(firebaseuserid)

            }
            else{
                Toast.makeText(this, "gagal", Toast.LENGTH_SHORT).show()

            }
        }




    }
    private fun savedata() {
        val ref =   FirebaseDatabase.getInstance().getReference("USERS")
        val userId = ref.push().key.toString()
        val nama = RegisterBinding.edtNama.text.toString().trim()

        val email = RegisterBinding.edtEmail.text.toString().trim()
        val password = RegisterBinding.edtPassword.text.toString().trim()
        val user =  users(userId, nama, email, password)

        Toast.makeText(this, user.toString(), Toast.LENGTH_SHORT).show()
        if (userId != null) {
            ref.child(userId).push().setValue(users(userId, nama, email, password)).addOnCompleteListener {
                Toast.makeText(this, "Successs", Toast.LENGTH_SHORT).show()
                RegisterBinding.edtNama.setText("")
                RegisterBinding.edtEmail.setText("")
                RegisterBinding.edtPassword.setText("")

            }
        }


    }
    private fun Activity.toast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

}