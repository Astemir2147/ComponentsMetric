package com.example.feature_components.presentation

import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import com.example.feature_components.R
import com.example.feature_components.data.extension.snackBar
import com.example.feature_components.data.model.Component
import com.example.feature_components.databinding.FragmentComponentsBinding
import com.example.feature_components.presentation.adapter.ComponentAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class ComponentsFragment : Fragment(R.layout.fragment_components), SearchView.OnQueryTextListener {
    private lateinit var componentsBinding: FragmentComponentsBinding
    private val binding get() = componentsBinding
    private lateinit var componentsAdapter: ComponentAdapter
    private val componentsViewModel by viewModel<ComponentsViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        upsertComponents()
        componentsBinding = FragmentComponentsBinding.bind(view)
        binding.searchView.setOnQueryTextListener(this)
        setFiltersClickListener()
        setAcceptedComponents()
    }

    /** Метод, проверяющий наличие подключения к сети, и исходя из этого
     * подгружает данные из firebase в room, или же сообщает об ошибке */
    private fun upsertComponents() {
        if (componentsViewModel.checkNetworkAvailability(requireContext())) {
            componentsViewModel.init()
        }
        else {
            val networkIsUnavailableMessage =
                requireActivity().resources.getString(R.string.network_is_unavailable)
            snackBar(networkIsUnavailableMessage)
        }
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

    override fun onQueryTextSubmit(query: String?): Boolean {
        if (query != null) {
                searchComponent(query)
        }
        return false
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        if (newText != null) {
            searchComponent(newText)
        }
        return true
    }

    private fun searchComponent(query: String) {
        val searchQuery = "%$query%"
        componentsViewModel.searchComponent(searchQuery).observe(viewLifecycleOwner,::setComponents)
    }
}
