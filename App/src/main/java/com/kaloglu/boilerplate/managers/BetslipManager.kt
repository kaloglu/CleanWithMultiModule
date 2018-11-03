/*
package com.kaloglu.boiler_plate.managers

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.kaloglu.boiler_plate.utility.extensions.formatDecimal
import java.util.ArrayList
import java.util.HashMap

object BetslipManager {
    private const val MAX_COUPON_RATE = 500000.00
    private const val DEFAULT_MULTIPLIER = 3
    private const val MAX_SELECTED_ODD_COUNT: Int = 30

    private var betsLive: MutableLiveData<EventIdMap> = MutableLiveData()
        get() {
            if (field.value == null)
                field.value = EventIdMap()
            return field
        }
    private var systemsLive: MutableLiveData<MutableList<Int>> = MutableLiveData()
        get() {
            if (field.value == null)
                field.value = mutableListOf()
            return field
        }
    private var bankoLive: MutableLiveData<MutableList<String>> = MutableLiveData()
        get() {
            if (field.value == null)
                field.value = mutableListOf()
            return field
        }

    val bets: LiveData<EventIdMap>
        get() = betsLive

    val bankoList: LiveData<MutableList<String>>
        get() = bankoLive

    val systemList: LiveData<MutableList<Int>>
        get() = systemsLive

    var multiplier = DEFAULT_MULTIPLIER
    var couponId: Long = -1

    fun addOrRemoveBet(event: Event?, odd: Odd?) {
        if (event == null || odd == null)
            return

        with(event) {
            when (!hasSelected(this, odd)) {
                true -> addBet(odd)
                false -> removeBet(odd)
            }
        }
    }

    fun isSelected(event: Event, odd: Odd) = hasSelected(event, odd)

    // region Rate
    private fun Double.invalidateRate() =
            when {
                this <= 0 -> 1.0
                this > MAX_COUPON_RATE -> MAX_COUPON_RATE
                else -> this
            }
    //endregion

    //region Banko
    fun addRemoveBanko(eventId: String): String =
            synchronized(bankoList) {
                when (bankoList.value?.contains(eventId)) {
                    true -> removeBanko(eventId)
                    false -> addBanko(eventId)
                }

                return bankoList.toString()
            }

    private fun addBanko(eventId: String) {
        bankoList.value?.add(eventId)
        bankoLive.postValue(bankoList.value)
    }

    private fun removeBanko(eventId: String) {
        bankoList.value?.remove(eventId)
        bankoLive.postValue(bankoList.value)
    }

    @JvmOverloads
    private fun updateBankoList(events: List<Event> = emptyList()) {
        bankoList.value?.clear()
        events?.asSequence()
                ?.filter { it.canBanko && it.isBanko }
                ?.map { it.idStr }
                ?.toMutableList()
                ?.run { bankoList.value?.addAll(this) }
                .also { bankoLive.postValue(bankoList.value) }
    }
    //endregion

    //region Systems

    fun setSystemsWithString(systems: String) = setSystems(systems.split(",").asSequence().map { it.toInt() }.toMutableList())

    @JvmOverloads
    fun setSystems(systems: List<Int>, availableSystems: List<Int>? = null) {
        systemList.value?.clear()
        checkPossibleSystems(systems.toMutableList(), availableSystems)
        systemList.value?.addAll(systems)
        systemsLive.postValue(systemList.value)
    }

    private fun checkPossibleSystems(systems: List<Int>, availableSystems: List<Int>? = null) {
        availableSystems?.also { available ->
            val iteratorSystem = systems.toMutableList().iterator()

            while (iteratorSystem.hasNext()) {
                iteratorSystem.next().let {
                    if (!available.contains(it))
                        iteratorSystem.remove()
                }
            }
        }
    }

    //endregion

    //region receiver Event
    private fun hasSelected(event: Event, odd: Odd): Boolean {
        if (odd.oddType == null) return false

        return getOddList(event, odd.oddType)
                ?.contains(odd)
                ?: false
    }

    private fun checkCanAdd(event: Event): Boolean {
        return true
        */
/*when {
            //TODO: Uyarı mesajı verilebilir MAx 30 seçilebilir vs.
            bets.count >= MAX_SELECTED_ODD_COUNT -> false

            // TODO: Event başlama tarihine göre Maç başladı kontrolü eklenebilir
            else -> !isStarted
        }*//*

    }

    private fun Event.addBet(odd: Odd) {
        if (checkCanAdd(this)) {
            getOddList(this, odd.oddType, true)
                    ?.add(odd)
        }
    }

    private fun Event.removeBet(odd: Odd) {
        getOddList(this, odd.oddType)
                ?.remove(odd) */
/*?: true*//*

        bankoList.value?.remove(this.idStr)
    }
    //endregion

    //region Coupon
    // Tribün vb bi yerden Kuponu Hemen Oyna seçilmesi durumunda
    @JvmOverloads
    fun addCoupon(coupon: Coupon, playNow: Boolean = false): Boolean {
        coupon.events?.run {
            filterNot { it.isStarted }
                    .forEach { event ->
                        event.odds.forEach {
                            getOddList(event, it.oddType, true)?.add(it)
                        }
                    }
        }
        if (playNow) {
            updateBankoList(coupon.events ?: emptyList())
            setSystems(coupon.system)
        }
        couponId = coupon.couponId
        return true
    }
    //endregion

    //region Utility
    private fun clearCouponId() {
        couponId = -1
    }

    @JvmStatic
    private fun getOddList(event: Event, oddType: String, forAdd: Boolean = false) =
            getOddTypeMap(event, forAdd)
                    ?.get(oddType, forAdd)

    @JvmStatic
    private fun getOddTypeMap(event: Event, createNew: Boolean) =
            bets.value
                    ?.getOddType(event, createNew)

    // Event'in son Odd'unun UnSelected yapılması
    fun removeEvent(key: Event) {
        // Bir maç bile çıkarılırsa source coupon değişir.
        bets.value
                ?.remove(key)
                ?.also { clearCouponId() }
    }

    fun clear() {
        betsLive.postValue(EventIdMap())
        systemsLive.postValue(mutableListOf())
        bankoLive.postValue(mutableListOf())
    }

    //TODO: Betslip Validate için lazım olacak
    */
/* var selectedOdds: ArrayList<String> = arrayListOf()
         private set
         get() {
             bets?.entries
                     ?.flatMap { it.value.entries }
                     ?.flatMap { it.value }
                     ?.asSequence()
                     ?.filterNot { field.contains(it.name) }
                     ?.mapTo(field) { it.name }
             return field
         }*//*


    //endregion

    //region Inner Classes

    @Suppress("CanBePrimaryConstructorProperty")
    class OddList(eventId: String, oddTypeKey: String) : ArrayList<Odd>(arrayListOf()) {
        private var eventId = eventId
        private var oddTypeKey = oddTypeKey
        internal var rate = 0f

        override fun remove(element: Odd) = super.remove(element)
                .also {
                    bets.value
                            ?.run {
                                if (this@OddList.isEmpty()) {
                                    getOddType(eventId)
                                            ?.remove(oddTypeKey)
                                }
                                validate()
                            }
                }

        override fun clear() {
            this.rate = 0f
            super.clear()
        }

        override fun add(element: Odd) =
                !this.contains(element) && super.add(element)
                        .also { bets.value?.validate() }

        private fun resetCalculation() {
            rate = 0f
        }

        fun validate(): OddList {
            resetCalculation()
            rate = this.asSequence()
                    .map { it.oddChange }
                    .map { it.value.toFloat() }
                    .max() ?: 0f

            return this
        }

    }

    @Suppress("CanBePrimaryConstructorProperty")
    class OddTypeMap(eventId: String) : HashMap<String, OddList>(), Map<String, OddList> {
        private var eventId = eventId
        internal var rate = 0f
        internal var count = 0

        fun getOddList(oddTypeKey: String) = get(oddTypeKey)

        fun get(oddTypeKey: String, createNew: Boolean): OddList? {
            var oddList = super.get(oddTypeKey)

            if (oddList == null && createNew) {
                oddList = createOddList(oddTypeKey)
            }
            return oddList
        }

        private fun createOddList(oddTypeKey: String): OddList? {
            this[oddTypeKey] = OddList(eventId, oddTypeKey)
            return this[oddTypeKey]
        }

        override fun remove(key: String) = super.remove(key)
                .also {
                    bets.value
                            ?.run {
                                if (this@OddTypeMap.isEmpty()) remove(eventId)
                                validate()
                            }
                }

        fun validate(): OddTypeMap {
            resetCalculation()
            entries
                    .asSequence()
                    .asIterable()
                    .map { it.value.validate() }
                    .apply {
                        rate = sumByDouble { it.rate.toDouble() }.toFloat()
                        count = sumBy { it.size }
                    }

            return this

        }

        private fun resetCalculation() {
            rate = 0f
            count = 0
        }

    }

    class EventIdMap : HashMap<Event, OddTypeMap>(), Map<Event, OddTypeMap> {
        internal var count = 0
        internal var rate: Double = 1.00
            get() = field.invalidateRate()

        val countStr
            get() = count.toString()

        val rateStr
            get() = rate.formatDecimal()

        private fun resetCalculation() {
            rate = 0.00
            count = 0
        }

        fun getOddType(eventId: String) =
                filter { it.key.idStr == eventId }
                        .map { it.value }
                        .takeUnless { it.isEmpty() }
                        ?.first()

        @JvmOverloads
        fun getOddType(event: Event, createNew: Boolean = false): OddTypeMap? {
            val oddTypeMap = getOddType(event.idStr)

            if (oddTypeMap == null && createNew)
                return createOddTypeMap(event)

            return oddTypeMap
        }

        private fun createOddTypeMap(event: Event): OddTypeMap? {
            this[event] = OddTypeMap(event.idStr)
            return this[event]
        }

        fun remove(eventId: String) {
            bets.value
                    ?.filterKeys { it.idStr == eventId }
                    ?.map { it.key }
                    ?.forEach { remove(it) }
                    ?.also { betsLive.postValue(bets.value) }
        }

        override fun remove(key: Event): OddTypeMap? =
                super.remove(key)?.also { count -= it.count }

        fun validate() {
            resetCalculation()
            entries
                    .asSequence()
                    .asIterable()
                    .map { it.value.validate() }
                    .apply {
                        rate = sumByDouble { it.rate.toDouble() }
                        count = sumBy { it.count }
                    }
            betsLive.postValue(bets.value)
        }

        fun getEventList(): List<Event> {
            return mapTo(mutableListOf()) { eventIdMap ->
                val event = eventIdMap.key
                event.odds.clear()
                event.odds.addAll(
                        eventIdMap.value.flatMap {
                            it.value
                        })
                return@mapTo event
            }
        }

    }

//endregion
}
*/
