package com.example.feature_components.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.feature_components.R
import com.example.feature_components.data.model.Component
import com.example.feature_components.databinding.FragmentComponentsBinding
import com.example.feature_components.presentation.adapter.ComponentAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class ComponentsFragment : Fragment(R.layout.fragment_components) {
    private lateinit var componentsBinding: FragmentComponentsBinding
    private val binding get() = componentsBinding
    private lateinit var componentsAdapter: ComponentAdapter
    private val componentsViewModel by viewModel<ComponentsViewModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        componentsBinding = FragmentComponentsBinding.inflate(inflater, container, false)
        componentsViewModel.init()
        setFiltersClickListener()
        setAcceptedComponents()
        return binding.root
    }

    private fun setComponents(list: List<Component>) {
        if (list.isNotEmpty()) {
            binding.shimmerViewContainer.stopShimmer()
            binding.shimmerViewContainer.visibility = View.GONE
            componentsAdapter = ComponentAdapter(list)
            binding.rvPosts.adapter = componentsAdapter
        }
    }

    private fun setFiltersClickListener() {
        binding.filterInstalled.setOnClickListener { setInstalledComponents() }
        binding.filterAccept.setOnClickListener { setAcceptedComponents() }
        binding.filterDiscarded.setOnClickListener { setDiscardedComponents() }
    }

    private fun setAcceptedComponents() {
        componentsViewModel.getAcceptedContractsFromDb()
        componentsViewModel.acceptedContractListFromDb.observe(viewLifecycleOwner, ::setComponents)
    }

    private fun setInstalledComponents() {
        componentsViewModel.getInstalledComponentsFromDb()
        componentsViewModel.installedListFromDb.observe(viewLifecycleOwner, ::setComponents)
    }

    private fun setDiscardedComponents() {
        componentsViewModel.getDiscardedComponentsFromDb()
        componentsViewModel.discardedListFromDb.observe(viewLifecycleOwner, ::setComponents)
    }
}
