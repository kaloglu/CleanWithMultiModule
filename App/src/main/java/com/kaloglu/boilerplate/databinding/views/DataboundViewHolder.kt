package com.kaloglu.databinding.views

import android.databinding.ViewDataBinding
import android.support.v7.widget.RecyclerView

class DataboundViewHolder<out T : ViewDataBinding>(val binding: T, val viewType: Int) : RecyclerView.ViewHolder(binding.root)