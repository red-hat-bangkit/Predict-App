package com.RedHat.predict

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.RedHat.predict.databinding.ActivityMainBinding
import com.RedHat.predict.db.Data
import com.RedHat.predict.db.PredictAdapter
import com.RedHat.predict.home.SectionsPagerAdapter
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class MainActivity : AppCompatActivity() {

    companion object {
        const val ARG_section_username= "username"

    }
    lateinit var mGoogleSignInClient: GoogleSignInClient
    private lateinit var mainViewModel: MainViewModel
    private lateinit var adapter: PredictAdapter
    private val auth by lazy {
        FirebaseAuth.getInstance()
    }
    private lateinit var activityMainBinding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.reqtoken))
            .requestEmail()
            .build()
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso)
//        val sectionsPagerAdapter = SectionsPagerAdapter(this, supportFragmentManager)
//        activityMainBinding.viewPager.adapter = sectionsPagerAdapter
//        activityMainBinding.tabs.setupWithViewPager(activityMainBinding.viewPager)
//        supportActionBar?.elevation = 0f
        showRecyclerList()



    }



    private fun showRecyclerList(){
        val user = intent.getParcelableExtra(ARG_section_username) as? Data
        if (user != null ) {
            adapter = PredictAdapter()
            adapter.notifyDataSetChanged()
            activityMainBinding.rvPredict.layoutManager = LinearLayoutManager(this)
            activityMainBinding.rvPredict.adapter = adapter

            mainViewModel = ViewModelProvider(
                this,
                ViewModelProvider.NewInstanceFactory()
            ).get(MainViewModel::class.java)
            mainViewModel.getGith().observe(this, { Github ->
                if (Github != null) {
                    adapter.setData(Github)
                    activityMainBinding.progressBar.visibility = View.INVISIBLE
                }
            })
            activityMainBinding.progressBar.visibility = View.VISIBLE
            if (user != null) {
                mainViewModel.getListQuotes(user.locationname, user.setelahhari, user.tanggal)
            }
        }
    }
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)


        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//
//        if (item.itemId == R.id.btnlogout) {
//            FirebaseAuth.getInstance().signOut()
//
//            val intent = Intent(this@MainActivity, LoginActivity::class.java)
//            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
//            startActivity(intent)
//            finish()
//        }


        if (item.itemId == R.id.btnsearch) {


            val intent = Intent(this@MainActivity, search::class.java)
//            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
            finish()
        }

        return super.onOptionsItemSelected(item)
    }



}