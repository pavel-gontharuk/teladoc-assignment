package com.gontharuk.teladocassignment

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import com.gontharuk.teladocassignment.databinding.MainActivityBinding
import com.gontharuk.teladocassignment.romeoandjuliet.presentation.ui.WordsCountActivity

class MainActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = MainActivityBinding.inflate(layoutInflater).also { setContentView(it.root) }
        binding.btnRomeo.setOnClickListener {
            startActivity(Intent(this, WordsCountActivity::class.java))
        }
    }
}