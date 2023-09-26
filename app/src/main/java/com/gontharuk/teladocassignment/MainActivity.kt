package com.gontharuk.teladocassignment

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import com.gontharuk.teladocassignment.core.presentation.debouncedListener
import com.gontharuk.teladocassignment.databinding.MainActivityBinding
import com.gontharuk.teladocassignment.romeoandjuliet.presentation.ui.WordsCountActivity
import com.gontharuk.teladocassignment.twistandshout.presentation.ui.TwistActivity

class MainActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = MainActivityBinding.inflate(layoutInflater).also { setContentView(it.root) }
        binding.btnRomeo.debouncedListener {
            startActivity(Intent(this, WordsCountActivity::class.java))
        }
        binding.btnTwist.debouncedListener {
            startActivity(
                Intent(this, TwistActivity::class.java).putExtra(
                    TwistActivity.SEARCH, "thebeatles"
                )
            )
        }
    }
}