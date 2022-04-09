package com.example.feature_components.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.feature_components.R
import com.example.feature_components.data.model.Component
import com.example.feature_components.databinding.ItemComponentBinding

class ComponentAdapter(
    private val postComments: List<Component>
) : RecyclerView.Adapter<ComponentAdapter.ComponentViewHolder>() {

    class ComponentViewHolder(
        val itemBinding: ItemComponentBinding
    ) : RecyclerView.ViewHolder(itemBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ComponentViewHolder =
        ComponentViewHolder(
            ItemComponentBinding.bind(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_component, parent, false)
            )
        )

    override fun onBindViewHolder(holder: ComponentViewHolder, position: Int) {
        with(holder.itemBinding) {
            itemTitle.text = postComments[position].componentName
            dataAcceptOfItem.text = postComments[position].dateOfAccept
            personWhitchAccept.text = postComments[position].personWhitchAccept
            countOfItem.text = postComments[position].countOfItem
        }
    }

    override fun getItemCount() = postComments.size
}