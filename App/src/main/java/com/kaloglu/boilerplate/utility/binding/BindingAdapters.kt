package com.kaloglu.boilerplate.utility.binding

import android.databinding.BindingAdapter
import android.support.v7.widget.AppCompatEditText
import android.support.v7.widget.RecyclerView
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import com.kaloglu.databinding.model.RecyclerItem
import com.kaloglu.databinding.views.BindingRecyclerAdapter
import com.kaloglu.boilerplate.utility.extensions.focus
import com.kaloglu.boilerplate.utility.extensions.onActionInSoftKeyboard
import com.kaloglu.boilerplate.utility.interfaces.OnActionInSoftKeyboardListener


/**
 * Set item itemList to RecyclerView's adapter.
 *
 * @param recyclerView the recyclerView
 * @param items        the items to be represent in the recyclerView
 */
@BindingAdapter("items")
fun setItems(recyclerView: RecyclerView, items: MutableList<RecyclerItem>) {
    (recyclerView.adapter as BindingRecyclerAdapter).items = items
}

@BindingAdapter("onOkInSoftKeyboard")
fun onOkInSoftKeyboard(editText: AppCompatEditText, onActionInSoftKeyboardListener: OnActionInSoftKeyboardListener) {
    editText.onActionInSoftKeyboard(EditorInfo.IME_ACTION_DONE, onActionInSoftKeyboardListener)
}

@BindingAdapter("value")
fun bindEditText(editText: EditText, value: String) {
    if (editText.text.toString() != value) {
        editText.setText(value)
        editText.focus()
    }
}

@BindingAdapter("valueInt")
fun bindEditText(editText: EditText, value: Int) {
    if (editText.text.toString() != value.toString()) {
        editText.setText(value.toString())
        editText.focus()
    }
}
