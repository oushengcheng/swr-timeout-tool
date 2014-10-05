package com.aps.wicc.web.backing.edit;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.deltaspike.core.api.config.view.ViewConfig;

import com.aps.wicc.ejb.IncidentBean;
import com.aps.wicc.model.Incident;
import com.aps.wicc.web.Messages;
import com.aps.wicc.web.Pages;

@Named
@RequestScoped
public class EditSummaryBacking implements Serializable {

    private static final long serialVersionUID = 1L;
    private IncidentBean incidentBean;
    private CurrentEdit editingIncident;
    private Messages messages;

    public EditSummaryBacking() {
    }

    @Inject
    public EditSummaryBacking(IncidentBean incidentBean, CurrentEdit editingIncident, Messages messages) {
        this.incidentBean = incidentBean;
        this.editingIncident = editingIncident;
        this.messages = messages;
    }

    public List<Incident> getIncidents() {
        return this.incidentBean.getIncidentList();
    }

    public Class<? extends ViewConfig> newIncident() {
        editingIncident.setIncident(incidentBean.newIncident(messages.defaultFooter()));
        return Pages.Editdetail.class;
    }

    public Class<? extends ViewConfig> editIncident(final Incident incident) {
        editingIncident.setIncident(incident);
        return Pages.Editdetail.class;
    }

    public Class<? extends ViewConfig> viewPlanStatic() {
        return Pages.Staticview.class;
    }

    public Class<? extends ViewConfig> viewPlanScroll() {
        return Pages.Scrollingview.class;
    }

}
