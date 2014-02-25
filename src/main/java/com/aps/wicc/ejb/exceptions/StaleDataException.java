package com.aps.wicc.ejb.exceptions;

public class StaleDataException extends RuntimeException
{
    private static final long serialVersionUID = 1L;
    
    public StaleDataException(final Throwable cause) {
        super(cause);
    }
}
