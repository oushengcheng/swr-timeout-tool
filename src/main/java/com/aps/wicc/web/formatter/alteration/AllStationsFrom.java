package com.aps.wicc.web.formatter.alteration;

import static com.google.common.collect.Lists.newArrayList;

import java.util.List;

import com.aps.wicc.model.Alteration;
import com.aps.wicc.model.AlterationType;

class AllStationsFrom implements FormatItem {

    private List<String> locations;

    public AllStationsFrom(Alteration alteration) {
        locations = newArrayList(alteration.getLocation());
    }

    public AllStationsFrom(List<String> locations1, List<String> locations2) {
        locations = newArrayList(locations1);
        locations.addAll(locations2);
    }

    @Override
    public AlterationType getAlterationType() {
        return AlterationType.ALLSTATIONSFROM;
    }

    @Override
    public List<String> getLocations() {
        return locations;
    }

    @Override
    public boolean mergeable(FormatItem formatItem) {

        if (mergeableWithAllStaionsTo(formatItem)) {

            return true;

        } else {

            return formatItem.getAlterationType() == getAlterationType();

        }
    }

    private boolean mergeableWithAllStaionsTo(FormatItem formatItem) {
        return locations.size() == 1 &&  formatItem.getAlterationType() == AlterationType.ALLSTATIONSTO;
    }

    @Override
    public FormatItem merge(FormatItem formatItem) {

        if (mergeableWithAllStaionsTo(formatItem)) {

            return new AllStationsBetween(locations, formatItem.getLocations());

        } else {

            return new AllStationsFrom(locations, formatItem.getLocations());

        }
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("will call at all stations from");
        builder.append(" ");
        builder.append(new ListFormatter(locations).format());
        return builder.toString();
    }





}
