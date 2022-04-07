package com.example.feature_insert_data.presentation

import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import android.view.View
import android.view.ViewGroup
import com.example.feature_insert_data.R
import com.example.feature_insert_data.databinding.FragmentInsertDataBinding

class InsertDataFragment : Fragment(R.layout.fragment_insert_data) {

    private var homePageBinding: FragmentInsertDataBinding? = null
    private val binding get() = homePageBinding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        homePageBinding = FragmentInsertDataBinding.inflate(inflater, container, false)
        return binding.root
    }

}