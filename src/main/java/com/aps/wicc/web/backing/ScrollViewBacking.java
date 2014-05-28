package com.aps.wicc.web.backing;

import java.io.*;

import javax.inject.*;

import org.joda.time.DateTime;
import org.joda.time.Minutes;
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
public class ScrollViewBacking implements Serializable
{
    private static final long serialVersionUID = 1L;
    private String id;
    private String requiredid;
    private String scrollspeed;
    private String defaultscrollspeed;
    private IncidentBean incidentBean;
    private ViewNavigationHandler viewNavigationHandler;
    private Incident incident;
    
    public ScrollViewBacking() {
        super();
    }
    
    @Inject
    public ScrollViewBacking(IncidentBean incidentBean, 
    		                   ViewNavigationHandler viewNavigationHandler, 
    		                   @PlanViewIdParameter String requiredid,
    		                   @ScrollSpeed String defaultscrollspeed) {
        super();
        this.requiredid = requiredid;
        this.defaultscrollspeed = defaultscrollspeed;
        this.incidentBean = incidentBean;
        this.viewNavigationHandler = viewNavigationHandler;
    }
    
    @PreRenderView
    protected void preRenderView() {
    	// Protect pages using id passed in in url
        if (this.id == null || !this.id.equals(this.requiredid)) {
            this.viewNavigationHandler.navigateTo(Pages.Pagenotfound.class);
        }
        // Set default scroll speed
        if (this.scrollspeed == null) {
        	this.scrollspeed = defaultscrollspeed;
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
    
    public String getScrollspeed() {
		return scrollspeed;
	}

	public void setScrollspeed(String scrollspeed) {
		this.scrollspeed = scrollspeed;
	}

	public Incident getIncident() {
        return this.incident;
    }
	
	public Integer getIncidentAge() {
    	return Minutes.minutesBetween(incident.getLastPublished(), new DateTime()).getMinutes();
    }
}
