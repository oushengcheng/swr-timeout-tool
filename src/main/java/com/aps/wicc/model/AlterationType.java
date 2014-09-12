package com.aps.wicc.model;

import java.util.List;

import com.google.common.collect.Lists;

public enum AlterationType {

    NEWORIGIN(true, "enum.alterationtype.neworigin"),
    CALLADD(true, "enum.alterationtype.calladd"),
    NOTCALL(true, "enum.alterationtype.notcall"),
    RUNVIA(true, "enum.alterationtype.runvia"),
    NEWDEST(true, "enum.alterationtype.newdest"),
    STARTFINISH(true, "enum.alterationtype.startfinish"),
    FINISHSTART(true, "enum.alterationtype.finishstart"),
    ALLSTATIONSFROM(true, "enum.alterationtype.allstationsfrom"),
    ALLSTATIONSTO(true, "enum.alterationtype.allstationsto"),
    ALLSTATIONSBETWEEN(false, "enum.alterationtype.allstationsbetween"),
    THENASBOOKEDFROM(true, "enum.alterationtype.thenasbookedfrom"),
    THENASBOOKEDTO(true, "enum.alterationtype.thenasbookedto"),
    THENASBOOKEDBETWEEN(false, "enum.alterationtype.thenasbookedbetween"),
    RUNFASTFROM(true, "enum.alterationtype.runfastfrom"),
    RUNFASTTO(true, "enum.alterationtype.runfastto"),
    RUNFASTBETWEEN(false, "enum.alterationtype.runfastbetween"),
    REVERSEAT(true, "enum.alterationtype.reverseat");

    private boolean visible;
    private String message;

    private AlterationType(boolean visible, String message) {
        this.visible = visible;
        this.message = message;
    }

    public boolean isVisible() {
        return visible;
    }

    public String getMessage() {
        return message;
    }

    public static AlterationType[] visibleValues() {
        List<AlterationType> filtered = Lists.newArrayList();
        for (AlterationType type : values()) {
            if (type.isVisible()) {
                filtered.add(type);
            }
        }
        return filtered.toArray(new AlterationType[filtered.size()]);
    }

}
