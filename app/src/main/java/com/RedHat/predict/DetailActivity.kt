package com.RedHat.predict

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.RedHat.predict.databinding.ActivityDetailBinding
import com.RedHat.predict.databinding.ActivitySearchBinding
import com.RedHat.predict.db.Data

class DetailActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_NOTE = "extra_note"
    }
    private lateinit var binding: ActivityDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val user = intent.getParcelableExtra(EXTRA_NOTE) as? Data

    }
}