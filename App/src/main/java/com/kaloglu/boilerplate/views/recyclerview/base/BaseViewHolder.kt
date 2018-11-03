package com.kaloglu.boilerplate.views.recyclerview.base

import android.support.annotation.LayoutRes
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.kaloglu.boilerplate.utility.extensions.inflateView

/**
 * A base ViewHolder that handles click and long click events.
 */
abstract class BaseViewHolder<T> : RecyclerView.ViewHolder, View.OnClickListener, View.OnLongClickListener {

    private var onItemClickListener: OnItemClickListener? = null

    private var onItemLongClickListener: OnItemLongClickListener? = null

    /**
     * Constructor.
     *
     * @param itemView item view
     */
    constructor(itemView: View) : super(itemView)

    /**
     * Constructor.
     *
     * @param parentView parent
     * @param layoutId   layout resource to inflate
     */
    constructor(parentView: ViewGroup, @LayoutRes layoutId: Int)
            : this(parentView.inflateView(layoutId))

    /**
     * Calls to update the contents with the item.
     *
     *
     * Override to set up some private fields to be used by RecyclerView.
     */
    abstract fun bindItem(item: T)

    /**
     * Register a callback to be invoked when this item is clicked.
     *
     * @param onItemClickListener callback
     */
    fun setItemClickListener(onItemClickListener: OnItemClickListener?) {
        onItemClickListener?.let {
            this.onItemClickListener = onItemClickListener
            this.itemView.setOnClickListener(this)
        }
    }

    /**
     * Register a callback to be invoked when this item is clicked and held.
     *
     * @param onItemLongClickListener callback
     */
    fun setItemLongClickListener(onItemLongClickListener: OnItemLongClickListener?) {
        onItemLongClickListener?.let {
            this.onItemLongClickListener = onItemLongClickListener
            this.itemView.setOnLongClickListener(this)
        }
    }

    override fun onClick(itemView: View) {
        onItemClickListener?.onItemClick(itemView, adapterPosition)
    }

    override fun onLongClick(itemView: View): Boolean {
        onItemLongClickListener?.onItemLongClick(itemView, adapterPosition)
        return true
    }
}
