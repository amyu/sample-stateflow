package com.example.sample2021.ui.screen

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.sample2021.R
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class SampleActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sample)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(
                    R.id.container,
                    SampleFragment.newInstance()
                )
                .commitNow()
        }
    }
}