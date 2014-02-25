package com.aps.wicc.web.formatter;

import java.util.ArrayList;
import java.util.List;

import com.aps.wicc.model.Alteration;
import com.aps.wicc.model.AlterationType;
import com.google.common.collect.ImmutableList;

public class CompressedAlteration
{
    private AlterationType alterationType;
    private List<String> locations;
    
    public CompressedAlteration(final AlterationType alterationType) {
        super();
        this.locations = new ArrayList<String>();
        this.alterationType = alterationType;
    }
    
    public AlterationType getAlterationType() {
        return this.alterationType;
    }
    
    public List<String> getLocations() {
        return ImmutableList.copyOf(this.locations);
    }
    
    public void addLocation(final String location) {
        this.locations.add(location);
    }
    
    public static List<CompressedAlteration> build(final List<Alteration> alterations) {
        final List<CompressedAlteration> list = new ArrayList<CompressedAlteration>();
        CompressedAlteration compressed = null;
        for (final Alteration alteration : alterations) {
            if (compressed == null || compressed.getAlterationType() != alteration.getAlterationType()) {
                compressed = new CompressedAlteration(alteration.getAlterationType());
                compressed.addLocation(alteration.getLocation());
                list.add(compressed);
            }
            else {
                compressed.addLocation(alteration.getLocation());
            }
        }
        return list;
    }
}
