package com.aps.wicc.model;

public enum Direction
{
    BOTH("enum.direction.both"), 
    UP("enum.direction.up"), 
    DOWN("enum.direction.down");
    
    private String message;
    
    private Direction(final String message) {
        this.message = message;
    }
    
    public String getMessage() {
        return this.message;
    }
}
