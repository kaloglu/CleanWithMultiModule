package com.kaloglu.boilerplate.views.recyclerview.base

import android.view.View

/**
 * Interface definition for a callback to be invoked when an item has been clicked.
 */
interface OnItemClickListener {

    /**
     * Called when an item has been clicked.
     *
     * @param itemView clicked view
     * @param position position of the clicked item
     */
    fun onItemClick(itemView: View, position: Int)
}
