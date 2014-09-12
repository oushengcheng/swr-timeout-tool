package com.aps.wicc.web.formatter.alteration;

import static com.google.common.collect.Lists.newArrayList;

import java.util.List;

import com.aps.wicc.model.Alteration;
import com.aps.wicc.model.AlterationType;

class NewDest implements FormatItem {

    private List<String> locations;

    public NewDest(Alteration alteration) {
        locations = newArrayList(alteration.getLocation());
    }

    public NewDest(List<String> locations1, List<String> locations2) {
        locations = newArrayList(locations1);
        locations.addAll(locations2);
    }

    @Override
    public AlterationType getAlterationType() {
        return AlterationType.NEWDEST;
    }

    @Override
    public List<String> getLocations() {
        return locations;
    }

    @Override
    public boolean mergeable(FormatItem formatItem) {
        return formatItem.getAlterationType() == getAlterationType();
    }

    @Override
    public FormatItem merge(FormatItem formatItem) {
        return new NewDest(locations, formatItem.getLocations());
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("will terminate at");
        builder.append(" ");
        builder.append(new ListFormatter(locations).format());
        return builder.toString();
    }



}
