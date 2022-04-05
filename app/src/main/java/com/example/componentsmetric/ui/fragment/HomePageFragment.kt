package com.example.componentsmetric.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import android.view.View
import android.view.ViewGroup
import com.example.componentsmetric.R
import com.example.componentsmetric.databinding.FragmentHomePageBinding

class HomePageFragment : Fragment(R.layout.fragment_home_page) {

    private var homePageBinding: FragmentHomePageBinding? = null
    private val binding get() = homePageBinding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        homePageBinding = FragmentHomePageBinding.inflate(inflater, container, false)
        return binding.root
    }

}

