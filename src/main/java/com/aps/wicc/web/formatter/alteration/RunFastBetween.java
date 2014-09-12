package com.aps.wicc.web.formatter.alteration;

import java.util.List;

import com.aps.wicc.model.AlterationType;

class RunFastBetween extends AbstractBetweenFormatItem implements FormatItem {

    public RunFastBetween(List<String> locations1, List<String> locations2) {
        super(locations1, locations2);
    }

    @Override
    public AlterationType getAlterationType() {
        return AlterationType.RUNFASTBETWEEN;
    }

    @Override
    public String description() {
        return "will run fast between %s and %s";
    }

    @Override
    public String toString() {
        return super.toString();
    }



}
