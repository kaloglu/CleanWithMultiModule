package com.kaloglu.databinding.views

import android.databinding.ViewDataBinding
import android.support.v7.recyclerview.extensions.AsyncDifferConfig
import android.support.v7.recyclerview.extensions.ListAdapter
import android.support.v7.util.DiffUtil
import android.view.ViewGroup

//TODO: daha sonra binding adapter bu classtan extend edilecek.
abstract class DataBoundListAdapter<T, V : ViewDataBinding>(diffCallback: DiffUtil.ItemCallback<T>)
    : ListAdapter<T, DataboundViewHolder<V>>(AsyncDifferConfig.Builder<T>(diffCallback).build()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataboundViewHolder<V> {
        val binding = createBinding(parent, viewType)
        return DataboundViewHolder(binding, viewType)
    }

    protected abstract fun createBinding(parent: ViewGroup, viewType: Int): V

    override fun onBindViewHolder(holder: DataboundViewHolder<V>, position: Int) {
        bind(holder.binding, getItem(position))
        holder.binding.executePendingBindings()
    }

    protected abstract fun bind(binding: V, item: T)

}
