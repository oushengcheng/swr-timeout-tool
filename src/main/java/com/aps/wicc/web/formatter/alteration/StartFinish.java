package com.aps.wicc.web.formatter.alteration;

import static com.google.common.collect.Lists.newArrayList;

import java.util.List;

import com.aps.wicc.model.Alteration;
import com.aps.wicc.model.AlterationType;

class StartFinish implements FormatItem {

    private List<String> locations;

    public StartFinish(Alteration alteration) {
        locations = newArrayList(alteration.getLocation());
    }

    public StartFinish(List<String> locations1, List<String> locations2) {
        locations = newArrayList(locations1);
        locations.addAll(locations2);
    }

    @Override
    public AlterationType getAlterationType() {
        return AlterationType.STARTFINISH;
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
        return new StartFinish(locations, formatItem.getLocations());
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("will start and terminate at");
        builder.append(" ");
        builder.append(new ListFormatter(locations).format());
        return builder.toString();
    }


}
