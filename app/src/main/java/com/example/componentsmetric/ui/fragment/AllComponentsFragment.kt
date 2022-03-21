package com.example.componentsmetric.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.componentsmetric.R
import com.example.componentsmetric.databinding.FragmentAllComponentsBinding
import com.example.componentsmetric.ui.adapter.ComponentAdapter
import com.example.componentsmetric.ui.dto.ComponentListItem
import com.example.componentsmetric.ui.viewModel.ComponentsViewModel
import com.example.componentsmetric.ui.viewModel.ViewModelFactory
import org.koin.androidx.viewmodel.ext.android.viewModel

class AllComponentsFragment : Fragment(R.layout.fragment_all_components) {

    private lateinit var binding: FragmentAllComponentsBinding
    private lateinit var componentAdapter: ComponentAdapter
    private val componentsViewModel by viewModel<ComponentsViewModel>()



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentAllComponentsBinding.bind(view)
        lifecycleScope.launchWhenCreated {
            componentsViewModel.init()
            componentsViewModel.commentsLoaded.observe(viewLifecycleOwner, ::setComponents)
        }


    }
    private fun setComponents(list: List<ComponentListItem>) {
        componentAdapter = ComponentAdapter(list)
        binding.rvPosts.adapter = componentAdapter

    }
    companion object {
        @JvmStatic
        fun newInstance() = AllComponentsFragment()
    }
}