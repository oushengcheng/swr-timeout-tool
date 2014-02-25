package com.aps.wicc.model;

public enum AlterationType
{
    NEWORIGIN("enum.alterationtype.neworigin"), 
    CALLADD("enum.alterationtype.calladd"), 
    NOTCALL("enum.alterationtype.notcall"), 
    RUNVIA("enum.alterationtype.runvia"), 
    NEWDEST("enum.alterationtype.newdest");
    
    private String message;
    
    private AlterationType(final String message) {
        this.message = message;
    }
    
    public String getMessage() {
        return this.message;
    }
}
