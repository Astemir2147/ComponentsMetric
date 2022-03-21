package com.example.componentsmetric.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import com.example.componentsmetric.R
import com.example.componentsmetric.databinding.FragmentHomePageBinding

class HomePageFragment : Fragment(R.layout.fragment_home_page) {
    private lateinit var binding: FragmentHomePageBinding


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentHomePageBinding.bind(view)
        binding.btnGetAllProduct.setOnClickListener { getAllComponents() }

    }

    private fun getAllComponents(){
        val fragment = AllComponentsFragment.newInstance()
        parentFragmentManager.beginTransaction().replace(R.id.viewFragmentContainer,fragment).commit()
    }

    companion object {
        @JvmStatic
        fun newInstance() = HomePageFragment()
    }

}

