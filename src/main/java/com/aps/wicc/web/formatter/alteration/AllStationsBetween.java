package com.aps.wicc.web.formatter.alteration;

import java.util.List;

import com.aps.wicc.model.AlterationType;

class AllStationsBetween extends AbstractBetweenFormatItem implements FormatItem {

    public AllStationsBetween(List<String> locations1, List<String> locations2) {
        super(locations1, locations2);
    }

    @Override
    public AlterationType getAlterationType() {
        return AlterationType.ALLSTATIONSBETWEEN;
    }

    @Override
    public String description() {
        return "will call at all stations between %s and %s";
    }

    @Override
    public String toString() {
        return super.toString();
    }



}
