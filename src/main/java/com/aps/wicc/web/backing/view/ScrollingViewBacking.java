package com.aps.wicc.web.backing.view;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.deltaspike.core.api.config.view.controller.PreRenderView;
import org.apache.deltaspike.core.api.config.view.navigation.ViewNavigationHandler;
import org.joda.time.DateTime;
import org.joda.time.Minutes;
import org.omnifaces.cdi.ViewScoped;

import com.aps.wicc.ejb.IncidentBean;
import com.aps.wicc.ejb.parameters.ScrollSpeed;
import com.aps.wicc.model.Incident;

@Named
@ViewScoped
public class ScrollingViewBacking implements Serializable
{
    private static final long serialVersionUID = 1L;
    private String id;
    private String scrollspeed;
    private String defaultscrollspeed;
    private IncidentBean incidentBean;
    private Incident incident;

    public ScrollingViewBacking() {
        super();
    }

    @Inject
    public ScrollingViewBacking(IncidentBean incidentBean,
                                ViewNavigationHandler viewNavigationHandler,
                                @ScrollSpeed String defaultscrollspeed) {
        this.defaultscrollspeed = defaultscrollspeed;
        this.incidentBean = incidentBean;
    }

    @PreRenderView
    protected void preRenderView() {
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
