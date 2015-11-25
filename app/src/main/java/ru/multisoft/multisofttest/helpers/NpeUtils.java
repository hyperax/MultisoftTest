package ru.multisoft.multisofttest.helpers;

import android.support.annotation.NonNull;
import android.text.TextUtils;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class NpeUtils {

    @NonNull
    public static <T> T getNonNull(T value, @NonNull T ifNullValue) {
        return value == null? ifNullValue : value;
    }

    @NonNull
    public static <T> List<T> getNonNull(List<T> value) {
        return getNonNull(value, Collections.<T>emptyList());
    }

    @NonNull
    public static String getNonNull(String value) {
        return getNonNull(value, "");
    }

    @NonNull
    public static long[] getNonNull(long[] value) {
        return getNonNull(value, new long[0]);
    }

    @NonNull
    public static int[] getNonNull(int[] value) {
        return getNonNull(value, new int[0]);
    }

    @NonNull
    public static BigDecimal getNonNull(BigDecimal value) {
        return getNonNull(value, BigDecimal.ZERO);
    }

    public static boolean isEmpty(Collection collection) {
        return collection == null || collection.isEmpty();
    }

    public static boolean isEmpty(String string) {
        return TextUtils.isEmpty(string);
    }

    public static boolean equals(Object a, Object b) {
        return (a == null) ? (b == null) : a.equals(b);
    }

    public static boolean isEmpty(CharSequence cs) {
        return cs == null || cs.length() == 0;
    }
}
