package com.example.feature_components.presentation

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import com.example.feature_components.R
import com.example.feature_components.data.model.Component
import com.example.feature_components.databinding.FragmentComponentsBinding
import com.example.feature_components.presentation.adapter.ComponentAdapter
import com.google.android.material.snackbar.Snackbar
import org.koin.androidx.viewmodel.ext.android.viewModel

class ComponentsFragment : Fragment(R.layout.fragment_components), SearchView.OnQueryTextListener {
    private lateinit var componentsBinding: FragmentComponentsBinding
    private val binding get() = componentsBinding
    private lateinit var componentsAdapter: ComponentAdapter
    private val componentsViewModel by viewModel<ComponentsViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        upsertComponents()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        componentsBinding = FragmentComponentsBinding.inflate(inflater, container, false)

        binding.searchView.setOnQueryTextListener(this)
        setFiltersClickListener()
        setAcceptedComponents()
        return binding.root
    }

    /** Метод, проверяющий наличие подключения к сети, и исходя из этого
     * подгружает данные из firebase в room, или же сообщает об ошибке */
    private fun upsertComponents() {

        if (isOnline()) {
            componentsViewModel.init()
        }
        else {
            snackBar("Нет подключения к интернету")
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

    private fun snackBar(message : String) {
        val view = requireActivity().findViewById<View>(android.R.id.content)
        Snackbar.make(view, message, Snackbar.LENGTH_SHORT)
            .setTextColor(resources.getColor(R.color.light_green, null)).show()
    }

    private fun searchComponent(query: String) {
        val searchQuery = "%$query%"
        componentsViewModel.searchComponent(searchQuery).observe(viewLifecycleOwner,::setComponents)
    }

    /**
     * Функция проверки подключения к интернету (wifi или мобильная связь)
     */

    fun isOnline(): Boolean {
        val connectivityManager =
            requireActivity().getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (connectivityManager != null) {
            val capabilities =
                connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
            if (capabilities != null) {
                if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
                    return true
                } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
                    return true
                } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)) {
                    return true
                }
            }
        }
        return false
    }
}
