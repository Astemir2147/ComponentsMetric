package com.example.componentsmetric.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.componentsmetric.R
import com.example.componentsmetric.databinding.ItemComponentBinding
import com.example.componentsmetric.ui.dto.ComponentListItem

class ComponentAdapter (
    private val postComments: List<ComponentListItem>
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
        holder.itemBinding.componentName.text = postComments[position].productName
        holder.itemBinding.componentCount.text = postComments[position].countOfProduct
    }

    override fun getItemCount() = postComments.size
}