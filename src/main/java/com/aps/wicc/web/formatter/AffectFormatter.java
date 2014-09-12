package com.aps.wicc.web.formatter;

import java.util.ArrayList;
import java.util.List;

import com.aps.wicc.model.Alteration;
import com.aps.wicc.web.formatter.alteration.FormatItem;
import com.aps.wicc.web.formatter.alteration.FormatItemFactory;
import com.aps.wicc.web.formatter.alteration.ListFormatter;

class AffectFormatter {

    private List<Alteration> alterations;

    public AffectFormatter(List<Alteration> alterations) {
        this.alterations = alterations;
    }

    public String format() {

        FormatItem prevFormatItem = null;

        List<FormatItem> items = new ArrayList<>();

        for (Alteration alteration : alterations) {

            FormatItem formatItem = FormatItemFactory.create(alteration);

            if (prevFormatItem != null && prevFormatItem.mergeable(formatItem)) {

                FormatItem merged = prevFormatItem.merge(formatItem);

                items.remove(prevFormatItem);
                items.add(merged);
                prevFormatItem = merged;

            } else {

                items.add(formatItem);
                prevFormatItem = formatItem;
            }

        }

        String format = new ListFormatter(items).format();

        StringBuilder builder = new StringBuilder();

        if (!format.isEmpty()) {

            builder.append(" ").append(format);
        }

        return builder.toString();

    }

}
