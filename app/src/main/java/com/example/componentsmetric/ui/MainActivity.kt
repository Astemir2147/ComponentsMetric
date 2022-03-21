package com.example.componentsmetric.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.componentsmetric.R
import com.example.componentsmetric.ui.fragment.HomePageFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.viewFragmentContainer, HomePageFragment.newInstance())
                .commit()
        }



    }
}
