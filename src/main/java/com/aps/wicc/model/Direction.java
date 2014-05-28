package com.aps.wicc.model;

public enum Direction
{
    BOTH("enum.direction.both"), 
    REVERSE("enum.direction.reverse"), 
    FORWARD("enum.direction.forward");
    
    private String message;
    
    private Direction(final String message) {
        this.message = message;
    }
    
    public String getMessage() {
        return this.message;
    }
}
