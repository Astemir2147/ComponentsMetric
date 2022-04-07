package com.example.feature_components.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.feature_components.R
import com.example.feature_components.databinding.FragmentComponentsBinding

class ComponentsFragment : Fragment(R.layout.fragment_components) {
    private lateinit var componentsBinding: FragmentComponentsBinding
    private val binding get() = componentsBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        componentsBinding = FragmentComponentsBinding.inflate(inflater, container, false)
        return binding.root
    }

}
