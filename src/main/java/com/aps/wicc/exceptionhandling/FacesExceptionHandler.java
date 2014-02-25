package com.aps.wicc.exceptionhandling;

import org.apache.deltaspike.core.api.config.view.navigation.*;
import org.apache.deltaspike.core.api.exception.control.event.*;
import org.apache.deltaspike.core.api.exception.control.*;
import org.apache.deltaspike.core.api.exception.control.ExceptionHandler;

import javax.faces.context.*;
import javax.faces.application.*;
import javax.inject.Inject;

import com.aps.wicc.web.*;

import org.jboss.weld.context.*;

@ExceptionHandler
public class FacesExceptionHandler
{
	@Inject
    private ViewNavigationHandler viewNavigationHandler;
    
    void redirect(@Handles @FacesRequest final ExceptionEvent<Throwable> evt, final FacesContext facesContext) {
        if (evt.getException().getClass() == ViewExpiredException.class) {
            this.viewNavigationHandler.navigateTo(Pages.Timedout.class);
            evt.handledAndContinue();
        }
        else if (evt.getException().getClass() == NonexistentConversationException.class) {
            this.viewNavigationHandler.navigateTo(Pages.Timedout.class);
            evt.handledAndContinue();
        }
        else {
            this.viewNavigationHandler.navigateTo(Pages.Servererror.class);
            evt.handledAndContinue();
        }
    }
}
