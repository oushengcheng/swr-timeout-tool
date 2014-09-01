package com.aps.wicc.web.backing.edit;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.apache.deltaspike.core.api.config.view.ViewConfig;

import com.aps.wicc.ejb.IncidentBean;
import com.aps.wicc.model.Incident;
import com.aps.wicc.web.Pages;

@Named
public class EditSummaryBacking implements Serializable {

    private static final long serialVersionUID = 1L;
    private IncidentBean incidentBean;
    private CurrentEdit editingIncident;

    public EditSummaryBacking() {
    }

    @Inject
    public EditSummaryBacking(final IncidentBean incidentBean, final CurrentEdit editingIncident) {
        this.incidentBean = incidentBean;
        this.editingIncident = editingIncident;
    }

    public List<Incident> getIncidents() {
        return this.incidentBean.getAllForPreviousWeek();
    }

    public Class<? extends ViewConfig> newIncident() {
        editingIncident.setIncident(new Incident());
        return Pages.Editdetail.class;
    }

    public Class<? extends ViewConfig> editIncident(final Incident incident) {
        editingIncident.setIncident(incident);
        return Pages.Editdetail.class;
    }

    public Class<? extends ViewConfig> viewPlanStatic() {
        return Pages.Planview.class;
    }

    public Class<? extends ViewConfig> viewPlanScroll() {
        return Pages.Planviewscroll.class;
    }

}
