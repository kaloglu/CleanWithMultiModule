package com.kaloglu.boilerplate.views.recyclerview.base

import android.view.View

/**
 * Interface definition for a callback to be invoked when an item has been clicked and held.
 */
interface OnItemLongClickListener {

    /**
     * Called when an item has been clicked and held.
     *
     * @param itemView clicked and held view
     * @param position position of the clicked and held item
     */
    fun onItemLongClick(itemView: View, position: Int)
}
