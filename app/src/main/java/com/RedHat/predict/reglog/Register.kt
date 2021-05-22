package com.RedHat.predict.reglog

import android.app.Activity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.RedHat.predict.databinding.ActivityRegisterBinding
import com.RedHat.predict.db.users

import com.google.firebase.database.FirebaseDatabase

class Register : AppCompatActivity() {
    private lateinit var RegisterBinding: ActivityRegisterBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        RegisterBinding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(RegisterBinding.root)



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

        RegisterBinding.btnSubmit.setOnClickListener {
            savedata()
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