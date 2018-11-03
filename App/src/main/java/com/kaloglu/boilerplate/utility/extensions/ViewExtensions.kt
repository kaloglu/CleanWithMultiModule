@file:JvmName("ViewUtil")

package com.kaloglu.boilerplate.utility.extensions

import android.content.Context
import android.os.Build
import android.support.annotation.ColorRes
import android.support.annotation.DimenRes
import android.support.annotation.DrawableRes
import android.support.annotation.LayoutRes
import android.support.annotation.StringRes
import android.support.annotation.StyleRes
import android.support.v4.app.DialogFragment
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.content.ContextCompat
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import com.kaloglu.databinding.views.BindingRecyclerAdapter
import com.kaloglu.boilerplate.utility.interfaces.OnActionInSoftKeyboardListener
import com.kaloglu.boilerplate.views.recyclerview.base.BaseRecyclerViewAdapter
import kotlin.reflect.KClass

inline fun <reified T : Fragment> FragmentManager.findFragment(tag: String? = ""): T? = findFragmentByTag(tag) as T?

inline fun <reified T : Fragment> FragmentManager.findFragment(cls: KClass<T>): T? =
        findFragment(cls.java.simpleName) as T?

fun DialogFragment.showDialog(fragmentManager: FragmentManager) = show(fragmentManager, this::class.java.simpleName)

inline fun <reified T : DialogFragment> FragmentManager.dismiss(cls: KClass<T>) {
    findFragment(cls)?.dismiss()
}

/**
 * Set ImageView visibility as {@link android.view.View#GONE} if resources null or 0 (zero).
 *
 * @param resId resource identifier to be set as source
 * @param tintColor resource identifier to be set as tint color
 */
fun ImageView.src(
        @DrawableRes resId: Int?,
        @ColorRes tintColor: Int? = 0
) {
    when {
        resId != null && resId != 0 -> {
            setImageResource(resId)
            visibility = View.VISIBLE
            tintColor(tintColor)
        }
        else -> visibility = View.GONE
    }
}

fun ImageView.tintColor(
        @ColorRes tintColor: Int? = 0
) {
    when {
        tintColor != null && tintColor != 0 -> {
            setColorFilter(context.color(tintColor))
        }
        else -> colorFilter = null
    }
}

/**
 * Set TextView visibility as {@link android.view.View#GONE} if string is null or empty.
 */
fun TextView.text(
        text: String? = "",
        @StringRes textRes: Int? = 0
) {
    if (text != null && text.isNotEmpty()) {
        this.text = text
        visibility = View.VISIBLE
    } else if (textRes != null && textRes != 0) {
        setText(textRes)
        visibility = View.VISIBLE
    } else {
        visibility = View.GONE
    }
}

fun TextView.textColor(@ColorRes resId: Int? = 0) {
    if (resId != null && resId != 0) {
        setTextColor(context.color(resId))
    }
}

fun TextView.textSize(@DimenRes resId: Int? = 0) {
    if (resId != null && resId != 0) {
        setTextSize(TypedValue.COMPLEX_UNIT_PX, context.resources.getDimension(resId))
    }
}

fun TextView.textAppearance(@StyleRes resId: Int? = 0) {
    if (resId != null && resId != 0) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            setTextAppearance(resId)
        } else {
            @Suppress("DEPRECATION")
            setTextAppearance(context, resId)
        }
    }
}

/* Bunlara setBackgroundDrawable() denilirse kullanılmak istenen yerde elle import edilmezse
View'ın kendi methodlarına gitmeye çalışıyor ve deprecated uyarısı veriyor
Extension ve Receiver Class method isimlerinin çakışmaması için başlarındaki set prefixini kaldırdım.
*/
fun View.backgroundDrawable(@DrawableRes resId: Int? = 0) {

    setBackgroundResource(
            if (resId != null && resId != 0) resId
            else
                0
    )
}

fun View.backgroundColor(@ColorRes colorId: Int? = 0) {
    if (colorId != null && colorId != 0) {
        setBackgroundColor(context.color(colorId))
    }
}

fun View.showView(show: Boolean) {
    visibility = if (show) View.VISIBLE else View.GONE
}

fun View.setVisible(isVisible: Boolean) {
    visibility = if (isVisible) View.VISIBLE else View.INVISIBLE
}

fun Context.drawable(@DrawableRes resId: Int) = ContextCompat.getDrawable(this, resId)

fun Context.color(@ColorRes resId: Int) = ContextCompat.getColor(this, resId)

fun Context.dimension(@DimenRes resId: Int) = this.resources.getDimensionPixelSize(resId)

/**
 * if you don't have inflater
 *
 * parent.inflateVew(R.layout#Name)
 *
 * @param layoutId @LayoutRes
 * @param inflater
 *
 * @return View!
 */
@JvmOverloads
fun ViewGroup.inflateView(@LayoutRes layoutId: Int, inflater: LayoutInflater = LayoutInflater.from(context)): View =
        inflater.inflate(layoutId, this, false)

fun RecyclerView.setup(
        layoutManager: RecyclerView.LayoutManager? = null,
        adapter: BaseRecyclerViewAdapter) {
    this.layoutManager = layoutManager
    this.adapter = adapter
}

@JvmOverloads
fun RecyclerView.withDataBinding(
        linearLayoutManager: LinearLayoutManager? = null,
        recyclerAdapter: BindingRecyclerAdapter = BindingRecyclerAdapter()
) {
    layoutManager = linearLayoutManager ?: LinearLayoutManager(context)
    adapter = recyclerAdapter

}

fun EditText.focus() {
    requestFocus()
    if (length() > 0)
        setSelection(length())
}

fun EditText.onActionInSoftKeyboard(action: Int, onActionInSoftKeyboardListener: OnActionInSoftKeyboardListener) {
    onActionInSoftKeyboard(action, onActionInSoftKeyboardListener::onActionInSoftKeyboard)
}

fun EditText.onActionInSoftKeyboard(action: Int, onOkInSoftKeyboard: (View) -> Boolean) {
    setOnEditorActionListener(
            TextView.OnEditorActionListener { view, actionId, _ ->
                return@OnEditorActionListener when (actionId) {
                    action -> onOkInSoftKeyboard(view)
                    else -> false
                }
            }
    )
}