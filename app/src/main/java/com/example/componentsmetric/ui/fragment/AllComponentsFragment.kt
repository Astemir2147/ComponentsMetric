package com.example.componentsmetric.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import com.example.componentsmetric.R
import com.example.componentsmetric.databinding.FragmentAllComponentsBinding
import com.example.componentsmetric.ui.adapter.ComponentAdapter
import com.example.componentsmetric.ui.viewModel.ComponentsViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class AllComponentsFragment : Fragment(R.layout.fragment_all_components) {

    private lateinit var binding: FragmentAllComponentsBinding
    private lateinit var componentAdapter: ComponentAdapter
    private val componentsViewModel by viewModel<ComponentsViewModel>()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        }
    }

