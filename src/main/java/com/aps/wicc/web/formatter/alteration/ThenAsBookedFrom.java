package com.aps.wicc.web.formatter.alteration;

import static com.google.common.collect.Lists.newArrayList;

import java.util.List;

import com.aps.wicc.model.Alteration;
import com.aps.wicc.model.AlterationType;

class ThenAsBookedFrom implements FormatItem {

    private List<String> locations;

    public ThenAsBookedFrom(Alteration alteration) {
        locations = newArrayList(alteration.getLocation());
    }

    public ThenAsBookedFrom(List<String> locations1, List<String> locations2) {
        locations = newArrayList(locations1);
        locations.addAll(locations2);
    }

    @Override
    public AlterationType getAlterationType() {
        return AlterationType.THENASBOOKEDFROM;
    }

    @Override
    public List<String> getLocations() {
        return locations;
    }

    @Override
    public boolean mergeable(FormatItem formatItem) {

        if (mergeableWithThenAsBookedTo(formatItem)) {

            return true;

        } else {

            return formatItem.getAlterationType() == getAlterationType();

        }

    }

    private boolean mergeableWithThenAsBookedTo(FormatItem formatItem) {
        return locations.size() == 1 &&  formatItem.getAlterationType() == AlterationType.THENASBOOKEDTO;
    }

    @Override
    public FormatItem merge(FormatItem formatItem) {

        if (mergeableWithThenAsBookedTo(formatItem)) {

            return new ThenAsBookedBetween(locations, formatItem.getLocations());

        } else {

            return new ThenAsBookedFrom(locations, formatItem.getLocations());

        }

    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("will run as booked from");
        builder.append(" ");
        builder.append(new ListFormatter(locations).format());
        return builder.toString();
    }


}
