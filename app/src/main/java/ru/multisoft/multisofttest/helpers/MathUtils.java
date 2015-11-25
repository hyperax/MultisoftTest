package ru.multisoft.multisofttest.helpers;

import android.support.annotation.NonNull;

import java.math.BigDecimal;

public class MathUtils {

    private MathUtils() {
    }

    public static boolean greater(BigDecimal first, BigDecimal second) {
        return first.compareTo(second) == 1;
    }

    public static boolean greaterOrEqual(BigDecimal first, BigDecimal second) {
        return first.compareTo(second) >= 0;
    }

    public static boolean equal(BigDecimal first, BigDecimal second) {
        return first.compareTo(second) == 0;
    }

    public static boolean isPositive(BigDecimal value) {
        return greater(value, BigDecimal.ZERO);
    }

    public static boolean isNegative(BigDecimal value) {
        return greater(BigDecimal.ZERO, value);
    }

    @NonNull
    public static BigDecimal getValue(String str) {
        BigDecimal resultValue;
        if (NpeUtils.isEmpty(str)) {
            resultValue = BigDecimal.ZERO;
        } else {
            try {
                resultValue = new BigDecimal(str);
            } catch (Exception e) {
                resultValue = BigDecimal.ZERO;
            }
        }
        return resultValue;
    }

    public static int getFractionalCount(BigDecimal value) {
        String string = value.stripTrailingZeros().toPlainString();
        int index = string.indexOf(".");
        return index < 0 ? 0 : string.length() - index - 1;
    }

    public static int getIntegerCount(BigDecimal value) {
        int absIntValue = value.abs().intValue();
        return absIntValue > 0? String.valueOf(absIntValue).length() : 0;
    }

    public static boolean isValueMultiple(BigDecimal value, BigDecimal multiplicity) {
        return equal(value.remainder(multiplicity), BigDecimal.ZERO);
    }
}
