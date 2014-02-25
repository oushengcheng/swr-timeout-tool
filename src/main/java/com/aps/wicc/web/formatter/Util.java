package com.aps.wicc.web.formatter;

import java.util.*;

final class Util
{
    public static final <T> String formatList(final List<T> items, final ListFormatter<T> formatter) {
        final StringBuilder builder = new StringBuilder();
        final ListIterator<T> it = items.listIterator();
        while (it.hasNext()) {
            builder.append(formatter.toString(it.next()));
            if (it.nextIndex() < items.size() - 1) {
                builder.append(",").append(" ");
            }
            else {
                if (it.nextIndex() != items.size() - 1) {
                    continue;
                }
                builder.append(" ").append("and").append(" ");
            }
        }
        return builder.toString();
    }
}
