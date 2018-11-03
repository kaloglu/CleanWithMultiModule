package com.kaloglu.boilerplate.actionObserver

import android.view.View
import com.kaloglu.databinding.model.RecyclerItem

//TODO: Bunları hızlıca geçici olarak ekledim genel olarak tek satır yeterli oluyor. Yeri tartışılabilir.

open class ClearCoupon : Action<Boolean>(true)
open class CollapseCoupon : Action<Boolean>(true)
open class AddCoupon : Action<Boolean>(true)

sealed class ShowDialog(view: View) : Action<View>(view) {

    class System(view: View) : ShowDialog(view)
    class SystemCoupons(view: View) : ShowDialog(view)

    class Multiplier(view: View) : ShowDialog(view)
    class MultipleCoupon(view: View) : ShowDialog(view)
    class PlaySuccess(view: View) : ShowDialog(view)
    class SaveCoupon(view: View) : ShowDialog(view)
    class ShareCoupon(view: View) : ShowDialog(view)
}

open class OpenCouponDetail(couponId: Long) : Action<Long>(couponId)
open class OpenCouponPreferences(couponId: Long) : Action<Long>(couponId)
open class PlayCoupon(couponId: Long) : Action<Long>(couponId)
open class ShareCoupon(couponId: Long) : Action<Long>(couponId)
open class RenameCoupon(couponId: Long) : Action<Long>(couponId)
open class DeleteCoupons(couponId: Long) : Action<Long>(couponId)

sealed class Remove<VM : RecyclerItem>(view: View, viewModel: VM) : Action<Pair<View, VM>>(Pair(view, viewModel)) {

    class Item(view: View, viewModel: RecyclerItem) : Remove<RecyclerItem>(view, viewModel)

}
