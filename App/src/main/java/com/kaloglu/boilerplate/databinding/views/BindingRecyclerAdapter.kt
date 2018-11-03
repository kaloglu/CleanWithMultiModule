package com.kaloglu.databinding.views

import android.databinding.ViewDataBinding
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.kaloglu.boilerplate.BR
import com.kaloglu.databinding.model.RecyclerItem
import com.kaloglu.boilerplate.utility.extensions.inflateBinding

open class BindingRecyclerAdapter : RecyclerView.Adapter<BindingRecyclerAdapter.ViewHolder>() {

    var items: MutableList<RecyclerItem> = mutableListOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(parent.inflateBinding(viewType), viewType)
    }

    /**
     *
     * @param holder
     * @param position
     *
     * */
    final override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        //databinding eklendikten sonra burası açılmalı.
//        holder.binding.setVariable(BR.dataModel, items[position])

        items[position].parent?.let {
            holder.binding.setVariable(BR.parent, it)
        }
        onBindCustomVariable(holder, items[position])

    }

    /**
     *
     *  Set custom variable after #onBindViewHolder
     *
     *  binding.setVariable(BR.variableName, it)
     *
     *  @param binding ViewDatabinding
     *  @param item Observable
     *
     * */
    internal open fun onBindCustomVariable(holder: ViewHolder, item: RecyclerItem) = Unit

    override fun getItemCount(): Int {
        return items.size
    }

    override fun getItemViewType(position: Int): Int {
        return items[position].layoutId
    }

    open fun removeItem(item: RecyclerItem) {
        val pos = items.indexOf(item)
        if (pos >= 0) {
            items.removeAt(pos)
            notifyItemRemoved(pos)
        }
    }

    class ViewHolder(val binding: ViewDataBinding, val viewType: Int) : RecyclerView.ViewHolder(binding.root)
}
