package com.aps.wicc.model;

public enum Affect
{
    ALTERED("enum.affect.altered"), 
    CANCELLED("enum.affect.cancelled"), 
    DELAYED("enum.affect.delayed"), 
    UNAFFECTED("enum.affect.unaffected");
    
    private String message;
    
    private Affect(final String message) {
        this.message = message;
    }
    
    public String getMessage() {
        return this.message;
    }
}
