package com.aps.wicc.web.formatter.alteration;

import static com.google.common.collect.Lists.newArrayList;

import java.util.List;

import com.aps.wicc.model.Alteration;
import com.aps.wicc.model.AlterationType;

class RunFastTo implements FormatItem {

    private List<String> locations;

    public RunFastTo(Alteration alteration) {
        locations = newArrayList(alteration.getLocation());
    }

    public RunFastTo(List<String> locations1, List<String> locations2) {
        locations = newArrayList(locations1);
        locations.addAll(locations2);
    }

    @Override
    public AlterationType getAlterationType() {
        return AlterationType.RUNFASTTO;
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
        return new RunFastTo(locations, formatItem.getLocations());
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("will run fast to");
        builder.append(" ");
        builder.append(new ListFormatter(locations).format());
        return builder.toString();
    }

}
