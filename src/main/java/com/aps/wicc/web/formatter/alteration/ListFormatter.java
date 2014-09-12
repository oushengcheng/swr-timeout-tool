package com.aps.wicc.web.formatter.alteration;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class ListFormatter {

    private List<?> items;

    public ListFormatter(List<?> items) {
        this.items = new ArrayList<>(items);
    }

    public String format() {

        StringBuilder builder = new StringBuilder();

        ListIterator<?> it = items.listIterator();

        while (it.hasNext()) {

            builder.append(it.next().toString());

            if (it.nextIndex() < items.size() - 1) {

                builder.append(",").append(" ");

            } else if (items.size() > 1 && it.nextIndex() == items.size() - 1){

                builder.append(" ").append("and").append(" ");

            }

        }

        return builder.toString();
    }

}
