package com.aps.wicc.exceptionhandling;

import java.util.Iterator;

import javax.enterprise.inject.spi.BeanManager;
import javax.faces.FacesException;
import javax.faces.context.ExceptionHandler;
import javax.faces.context.ExceptionHandlerWrapper;
import javax.faces.event.ExceptionQueuedEvent;

import org.apache.deltaspike.core.api.exception.control.event.ExceptionToCatchEvent;
import org.apache.deltaspike.core.api.provider.BeanManagerProvider;

public class DeltaSpikeExceptionHandler extends ExceptionHandlerWrapper
{
    private final BeanManager beanManager;
    private final ExceptionHandler wrapped;
    
    public DeltaSpikeExceptionHandler(final ExceptionHandler wrapped) {
        super();
        this.wrapped = wrapped;
        this.beanManager = BeanManagerProvider.getInstance().getBeanManager();
    }
    
    public ExceptionHandler getWrapped() {
        return this.wrapped;
    }
    
    public void handle() throws FacesException {
       
    	Iterator<ExceptionQueuedEvent> it = this.getUnhandledExceptionQueuedEvents().iterator();
    	
        while (it.hasNext()) {
            try {
                ExceptionQueuedEvent evt = it.next();
                ExceptionToCatchEvent etce = new ExceptionToCatchEvent(evt.getContext().getException(), FacesRequestLiteral.INSTANCE);
                this.beanManager.fireEvent(etce);
            }
            finally {
                it.remove();
            }            
        }
        this.getWrapped().handle();
    }
}
