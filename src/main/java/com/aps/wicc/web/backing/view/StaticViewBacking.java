package com.aps.wicc.web.backing.view;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import org.joda.time.DateTime;
import org.joda.time.Minutes;
import org.omnifaces.cdi.ViewScoped;

import com.aps.wicc.ejb.IncidentBean;
import com.aps.wicc.model.Incident;

@Named
@ViewScoped
public class StaticViewBacking implements Serializable
{
    private static final long serialVersionUID = 1L;
    private IncidentBean incidentBean;
    private Incident incident;
    
    public StaticViewBacking() {
        super();
    }
    
    @Inject
    public StaticViewBacking(IncidentBean incidentBean) {
        super();
        this.incidentBean = incidentBean;        
    }
    
    @PostConstruct
    void init() {
        this.incident = this.incidentBean.getOpenIncident();
    }
    
    public Incident getIncident() {
        return incident;
    }
    
    public Integer getIncidentAge() {
    	return Minutes.minutesBetween(incident.getLastPublished(), new DateTime()).getMinutes();
    }
    
}
