package com.aps.wicc.exceptionhandling;

import javax.faces.context.*;

public class DeltaSpikeExceptionHandlerFactory extends ExceptionHandlerFactory
{
    private final ExceptionHandlerFactory parent;
    
    public DeltaSpikeExceptionHandlerFactory(final ExceptionHandlerFactory parent) {
        super();
        this.parent = parent;
    }
    
    public ExceptionHandler getExceptionHandler() {
        return new DeltaSpikeExceptionHandler(this.parent.getExceptionHandler());
    }
}
