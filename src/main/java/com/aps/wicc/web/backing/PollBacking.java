package com.aps.wicc.web.backing;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

@RequestScoped
@Named
public class PollBacking {

    @Inject FacesContext facesContext;

    @PostConstruct
    public void keepSessionAndConversationAlive() {

        facesContext.getExternalContext().getSession(false); // Keep session alive

    }
}
