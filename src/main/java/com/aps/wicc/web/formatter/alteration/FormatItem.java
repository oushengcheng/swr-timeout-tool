package com.aps.wicc.web.formatter.alteration;

import java.util.List;

import com.aps.wicc.model.AlterationType;

public interface FormatItem {

    public AlterationType getAlterationType();

    public List<String> getLocations();

    public boolean mergeable(FormatItem formatItem);

    public FormatItem merge(FormatItem formatItem);

}
