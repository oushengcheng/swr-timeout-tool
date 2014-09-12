package com.aps.wicc.web.formatter.alteration;

import static com.google.common.collect.Lists.newArrayList;

import java.util.List;

import com.aps.wicc.model.Alteration;
import com.aps.wicc.model.AlterationType;

abstract class AbstractBetweenFormatItem {

    List<String> locations;

    public AbstractBetweenFormatItem(Alteration alteration) {
        locations = newArrayList(alteration.getLocation());
    }

    public AbstractBetweenFormatItem(List<String> locations1, List<String> locations2) {
        locations = newArrayList(locations1);
        locations.addAll(locations2);
    }

    public abstract String description();

    public abstract AlterationType getAlterationType();

    public List<String> getLocations() {
        return locations;
    }

    public boolean mergeable(FormatItem formatItem) {
        return false;
    }

    public FormatItem merge(FormatItem formatItem) {
        throw new UnsupportedOperationException("Attempt to merge BetweenFormatItems");
    }

    @Override
    public String toString() {
        return String.format(description(), locations.get(0), locations.get(1));
    }

}
