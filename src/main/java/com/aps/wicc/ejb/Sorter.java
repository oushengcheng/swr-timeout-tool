package com.aps.wicc.ejb;

import java.io.Serializable;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.aps.wicc.model.Affect;
import com.aps.wicc.model.ServiceGroupAlteration;
import com.google.common.collect.ComparisonChain;
import com.google.common.collect.Lists;

public class Sorter implements Serializable
{
    private static final long serialVersionUID = 1L;
    
    public List<ServiceGroupAlteration> sort(final List<ServiceGroupAlteration> alterations) {
        final List<ServiceGroupAlteration> sorted = Lists.newArrayList(alterations);
        Collections.sort(sorted, new Comparator<ServiceGroupAlteration>() {
            @Override
            public int compare(final ServiceGroupAlteration serviceGroupAlteration0, final ServiceGroupAlteration serviceGroupAlteration1) {
                return ComparisonChain.start().compareTrueFirst(serviceGroupAlteration0.getAffect() == Affect.CANCELLED, serviceGroupAlteration1.getAffect() == Affect.CANCELLED).compare(serviceGroupAlteration0.getServiceGroup().getServiceGrouping().getPriority(), serviceGroupAlteration1.getServiceGroup().getServiceGrouping().getPriority()).compare(new StringBuilder(serviceGroupAlteration0.getServiceGroup().getHeadcode()).reverse().toString(), new StringBuilder(serviceGroupAlteration1.getServiceGroup().getHeadcode()).reverse().toString()).result();
            }
        });
        return sorted;
    }
}
