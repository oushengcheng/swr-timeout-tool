package com.aps.wicc.web.backing;

import java.io.*;

import javax.inject.*;

import org.omnifaces.cdi.*;

import com.aps.wicc.ejb.*;

import org.apache.deltaspike.core.api.config.view.navigation.*;

import com.aps.wicc.model.*;
import com.aps.wicc.ejb.initialisation.*;
import com.aps.wicc.web.*;

import org.apache.deltaspike.core.api.config.view.controller.*;

import javax.annotation.*;

@Named
@ViewScoped
public class IncidentViewBacking implements Serializable
{
    private static final long serialVersionUID = 1L;
    private String id;
    private String requiredid;
    private IncidentBean incidentBean;
    private ViewNavigationHandler viewNavigationHandler;
    private Incident incident;
    
    public IncidentViewBacking() {
        super();
    }
    
    @Inject
    public IncidentViewBacking(final IncidentBean incidentBean, final ViewNavigationHandler viewNavigationHandler, @PlanViewIdParameter final String requiredid) {
        super();
        this.requiredid = requiredid;
        this.incidentBean = incidentBean;
        this.viewNavigationHandler = viewNavigationHandler;
    }
    
    @PreRenderView
    protected void preRenderView() {
        if (this.id == null || !this.id.equals(this.requiredid)) {
            this.viewNavigationHandler.navigateTo(Pages.Pagenotfound.class);
        }
    }
    
    @PostConstruct
    void init() {
        this.incident = this.incidentBean.getOpenIncident();
    }
    
    public String getId() {
        return this.id;
    }
    
    public void setId(final String id) {
        this.id = id;
    }
    
    public Incident getIncident() {
        return this.incident;
    }
}
