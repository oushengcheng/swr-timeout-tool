package com.aps.wicc.web.formatter.alteration;

import java.util.List;

import com.aps.wicc.model.AlterationType;

class ThenAsBookedBetween extends AbstractBetweenFormatItem implements FormatItem {

    public ThenAsBookedBetween(List<String> locations1, List<String> locations2) {
        super(locations1, locations2);
    }

    @Override
    public AlterationType getAlterationType() {
        return AlterationType.RUNFASTBETWEEN;
    }

    @Override
    public String description() {
        return "will run as booked between %s and %s";
    }

    @Override
    public String toString() {
        return super.toString();
    }



}
