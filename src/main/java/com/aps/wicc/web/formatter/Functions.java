package com.aps.wicc.web.formatter;

import com.aps.wicc.model.ServiceGroupAlteration;

public final class Functions {

    public static String format(ServiceGroupAlteration serviceGroupAlteration) {
        return new LongFormatter(serviceGroupAlteration).format();
    }

    public static String shortFormat(ServiceGroupAlteration serviceGroupAlteration) {
        return new ShortFormatter(serviceGroupAlteration).format();
    }

    public static String choice(Integer value, Integer lower, Integer upper, String lowerString, String middleString, String upperString) {

        if (value.compareTo(lower) < 0 ) {

            return lowerString;

        } else if (value.compareTo(upper) >= 0) {

            return upperString;

        } else {

            return middleString;

        }

    }
}
