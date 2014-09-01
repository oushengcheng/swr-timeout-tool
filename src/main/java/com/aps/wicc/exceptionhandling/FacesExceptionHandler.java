package com.aps.wicc.exceptionhandling;

import javax.faces.application.ViewExpiredException;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import org.apache.deltaspike.core.api.config.view.navigation.ViewNavigationHandler;
import org.apache.deltaspike.core.api.exception.control.ExceptionHandler;
import org.apache.deltaspike.core.api.exception.control.Handles;
import org.apache.deltaspike.core.api.exception.control.event.ExceptionEvent;

import com.aps.wicc.web.Pages;

@ExceptionHandler
public class FacesExceptionHandler
{
    @Inject
    private ViewNavigationHandler viewNavigationHandler;

    void redirect(@Handles @FacesRequest final ExceptionEvent<Throwable> evt, final FacesContext facesContext) {

        if (evt.getException().getClass() == ViewExpiredException.class) {

            this.viewNavigationHandler.navigateTo(Pages.Timedout.class);

            evt.handledAndContinue();

        } else {

            this.viewNavigationHandler.navigateTo(Pages.Servererror.class);

            evt.handledAndContinue();

        }
    }
}
