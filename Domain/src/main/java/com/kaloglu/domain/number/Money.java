package com.kaloglu.domain.number;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

public final class Money implements Serializable {

    public final static Money ZERO = Money.of(0);

    private final static DecimalFormatSymbols decimalFormatSymbols = provideDecimalFormatSymbols();

    private final static DecimalFormat decimalFormat = new DecimalFormat("#,##0.00 TL", decimalFormatSymbols);

    private BigDecimal value = BigDecimal.ZERO;

    private Money(BigDecimal b) {
        value = b;
    }

    /**
     * Create Money object with zero value
     *
     * @return Created Money object with zero value
     */
    public static Money zero() {
        return ZERO.clone();
    }

    /**
     * Create Money object from an integer value
     *
     * @return Created Money object
     */
    public static Money of(int amount) {
        return Money.of(new BigDecimal(amount));
    }

    /**
     * Create Money object from a long value
     *
     * @return Created Money object
     */
    public static Money of(long amount) {
        return Money.of(new BigDecimal(amount));
    }

    /**
     * Create Money object from a double value
     *
     * @return Created Money object
     */
    public static Money of(double amount) {
        return Money.of(new BigDecimal(amount));
    }


    /**
     * Create Money object from a big decimal value
     *
     * @return Created Money object
     */
    public static Money of(BigDecimal amount) {
        return new Money(amount.setScale(2, RoundingMode.HALF_EVEN));
    }

    /**
     * Add Money value into current object and update
     *
     * @return Updated Money object
     */
    public Money add(Money money) {
        value = value.add(money.value);
        return this;
    }

    /**
     * Subtract Money value from current object and update
     *
     * @return Updated Money object
     */
    public Money subtract(Money money) {
        value = value.subtract(money.value);
        return this;
    }

    /**
     * Create new Money object by multiply with an integer value
     *
     * @return New Money object with result of multiply operation
     */
    public Money multiply(int val) {
        return multiply(new BigDecimal(val));
    }

    /**
     * Create new Money object by multiply with a long value
     *
     * @return New Money object with result of multiply operation
     */
    public Money multiply(long val) {
        return multiply(new BigDecimal(val));
    }

    /**
     * Create new Money object by multiply with a double value
     *
     * @return New Money object with result of multiply operation
     */
    public Money multiply(double val) {
        return multiply(new BigDecimal(val));
    }

    /**
     * Create new Money object by multiply with a big decimal value
     *
     * @return New Money object with result of multiply operation
     */
    public Money multiply(BigDecimal val) {
        return Money.of(value.multiply(val));
    }

    /**
     * Create new Money object by divide with an integer value
     *
     * @return New Money object with result of divide operation
     */
    public Money divide(int val) {
        return divide(new BigDecimal(val));
    }

    /**
     * Create new Money object by divide with a long value
     *
     * @return New Money object with result of long operation
     */
    public Money divide(long val) {
        return divide(new BigDecimal(val));
    }

    /**
     * Create new Money object by divide with a double value
     *
     * @return New Money object with result of divide operation
     */
    public Money divide(double val) {
        return divide(new BigDecimal(val));
    }

    /**
     * Create new Money object by divide with a big decimal value
     *
     * @return New Money object with result of divide operation
     */
    public Money divide(BigDecimal val) {
        return Money.of(value.divide(val, RoundingMode.HALF_EVEN));
    }

    @Override
    public boolean equals(Object o) {
        return o.hashCode() == hashCode();
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

    /**
     * Stringify Money value
     *
     * @return String value of Money (000.000,00 TL)
     */
    @Override
    public String toString() {
        return decimalFormat.format(value);
    }

    /**
     * Create Money Format Builder to custom string format
     *
     * @return Created Money Format Builder object
     */
    public MoneyFormatBuilder toFormatBuilder() {
        return new MoneyFormatBuilder(this);
    }

    @Override
    public Money clone() {
        return Money.of(value);
    }

    /**
     * Check if Money value has penny (or kuruş) value
     *
     * @return true if Money has penny (or kuruş) value
     */
    public boolean hasPenny() {
        return value.remainder(BigDecimal.ONE).compareTo(BigDecimal.ZERO) != 0;
    }

    /**
     * Check if Money value less than zero
     *
     * @return true if Money value less than zero
     */
    public boolean isZero() {
        return value.compareTo(BigDecimal.ZERO) == 0;
    }

    /**
     * Check if Money value less than zero
     *
     * @return true if Money value less than zero
     */
    public boolean isLessThanZero() {
        return value.compareTo(BigDecimal.ZERO) < 0;
    }

    private static DecimalFormatSymbols provideDecimalFormatSymbols() {
        DecimalFormatSymbols symbols = new DecimalFormatSymbols();
        symbols.setDecimalSeparator(',');
        symbols.setGroupingSeparator('.');
        return symbols;
    }

    public static class MoneyFormatBuilder {

        private final static DecimalFormat withPennyDecimalFormat = new DecimalFormat("#,##0.00", decimalFormatSymbols);
        private final static DecimalFormat withoutPennyDecimalFormat = new DecimalFormat("#,##0", decimalFormatSymbols);

        Money money;

        boolean withSign = false;
        boolean withPenny = false;
        boolean alwaysPenny = false;

        private MoneyFormatBuilder(Money money) {
            this.money = money;
        }

        /**
         * Add TL sign into format
         *
         * @return Updated Money Format Builder object
         */
        public MoneyFormatBuilder sign() {
            this.withSign = true;
            return this;
        }

        /**
         * Add decimal values (penny or kuruş) into format
         *
         * @return Updated Money Format Builder object
         */
        public MoneyFormatBuilder penny(boolean alwaysPenny) {
            this.withPenny = true;
            this.alwaysPenny = alwaysPenny;
            return this;
        }

        /**
         * Stringify Money value with Money Format Builder
         *
         * @return String value of Money
         */
        @Override
        public String toString() {
            String formatted;
            if (alwaysPenny) {
                formatted = withPennyDecimalFormat.format(money.value);
            } else if (withPenny) {
                if (money.hasPenny()) formatted = withPennyDecimalFormat.format(money.value);
                else
                    formatted = withoutPennyDecimalFormat.format(money.value);
            } else {
                formatted = withoutPennyDecimalFormat.format(money.value);
            }

            return withSign ?
                    formatted + " TL" :
                    formatted;
        }
    }
}