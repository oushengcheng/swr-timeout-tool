package com.aps.wicc.web.formatter.alteration;

import static com.google.common.collect.Lists.newArrayList;

import java.util.List;

import com.aps.wicc.model.Alteration;
import com.aps.wicc.model.AlterationType;

class RunFastFrom implements FormatItem {

    private List<String> locations;

    public RunFastFrom(Alteration alteration) {
        locations = newArrayList(alteration.getLocation());
    }

    public RunFastFrom(List<String> locations1, List<String> locations2) {
        locations = newArrayList(locations1);
        locations.addAll(locations2);
    }

    @Override
    public AlterationType getAlterationType() {
        return AlterationType.RUNFASTFROM;
    }

    @Override
    public List<String> getLocations() {
        return locations;
    }

    @Override
    public boolean mergeable(FormatItem formatItem) {

        if (mergeableWithRunFastTo(formatItem)) {

            return true;

        } else {

            return formatItem.getAlterationType() == getAlterationType();

        }
    }

    private boolean mergeableWithRunFastTo(FormatItem formatItem) {
        return locations.size() == 1 &&  formatItem.getAlterationType() == AlterationType.RUNFASTTO;
    }

    @Override
    public FormatItem merge(FormatItem formatItem) {

        if (mergeableWithRunFastTo(formatItem)) {

            return new RunFastBetween(locations, formatItem.getLocations());

        } else {

            return new RunFastFrom(locations, formatItem.getLocations());
        }

    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("will run fast from");
        builder.append(" ");
        builder.append(new ListFormatter(locations).format());
        return builder.toString();
    }

}
