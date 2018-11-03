package com.kaloglu.boilerplate.utility.interfaces

import android.text.Editable
import android.text.TextWatcher

interface SimpleTextWatcher : TextWatcher {
    override fun afterTextChanged(editable: Editable) = Unit

    override fun beforeTextChanged(beforeSequence: CharSequence, start: Int, count: Int, after: Int) = Unit

    override fun onTextChanged(charSequence: CharSequence, start: Int, before: Int, count: Int) = Unit
}
