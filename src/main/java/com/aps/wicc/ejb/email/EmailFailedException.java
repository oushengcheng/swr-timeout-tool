package com.aps.wicc.ejb.email;

public class EmailFailedException extends RuntimeException
{
    private static final long serialVersionUID = 1L;
    
    public EmailFailedException(final Throwable cause) {
        super(cause);
    }
}
