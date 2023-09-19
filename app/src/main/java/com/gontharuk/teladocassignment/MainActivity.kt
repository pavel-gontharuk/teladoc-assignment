package com.gontharuk.teladocassignment

import android.app.Activity
import android.os.Bundle
import com.gontharuk.teladocassignment.databinding.MainActivityBinding

class MainActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MainActivityBinding.inflate(layoutInflater).also {
            setContentView(it.root)
        }
    }
}