package com.aps.wicc.exceptionhandling;

import org.slf4j.*;
import org.apache.deltaspike.core.api.exception.control.event.*;
import org.apache.deltaspike.core.api.exception.control.*;

@ExceptionHandler
public class LogExceptionHandler
{
    private static final Logger logger;
    
    static {
        logger = LoggerFactory.getLogger(LogExceptionHandler.class);
    }
    
    void logExceptions(@BeforeHandles final ExceptionEvent<Throwable> evt) {
        LogExceptionHandler.logger.warn("An error occured: " + evt.getException().getMessage(), evt.getException());        
        evt.handledAndContinue();
    }
}
