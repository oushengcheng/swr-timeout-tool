package com.aps.wicc.web.backing.edit;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.deltaspike.core.api.config.view.ViewConfig;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.format.DateTimeFormat;

import com.aps.wicc.ejb.IncidentBean;
import com.aps.wicc.model.Incident;
import com.aps.wicc.model.ServiceGroupAlteration;
import com.aps.wicc.web.Messages;
import com.aps.wicc.web.Pages;
import com.aps.wicc.web.formatter.LongFormatter;

@Named
@RequestScoped
public class EditSummaryBacking implements Serializable {

    private static final long serialVersionUID = 1L;
    private IncidentBean incidentBean;
    private CurrentEdit editingIncident;
    private Messages messages;
    private DateTimeZone dateTimeZone;

    public EditSummaryBacking() {
    }

    @Inject
    public EditSummaryBacking(IncidentBean incidentBean,
    		                  CurrentEdit editingIncident,
    		                  Messages messages,
    		                  DateTimeZone dateTimeZone) {
        this.incidentBean = incidentBean;
        this.editingIncident = editingIncident;
        this.messages = messages;
        this.dateTimeZone = dateTimeZone;
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

    public Class<? extends ViewConfig> sleepIncident() {
        editingIncident.setIncident(createNewSleepIncident());
        return Pages.Editdetail.class;
    }

	private Incident createNewSleepIncident() {
		return incidentBean.newIncident(messages.sleepIncidentTitle(printTime()),
        		                        messages.sleepIncidentDescription(),
        		                        messages.defaultFooter());
	}

    public Class<? extends ViewConfig> actionIncident() {
        editingIncident.setIncident(createNewActionIncident());
        return Pages.Editdetail.class;
    }

	private Incident createNewActionIncident() {
		return incidentBean.newIncident(messages.actionIncidentTitle(printTime()),
        		                        messages.actionIncidentDescription(),
        		                        messages.defaultFooter());
	}

    public Class<? extends ViewConfig> viewPlanStatic() {
        return Pages.Staticview.class;
    }

    public Class<? extends ViewConfig> viewPlanScroll() {
        return Pages.Scrollingview.class;
    }

    private String printTime() {
        return DateTimeFormat.forPattern("HH:mm EEEE d MMMM yyyy").withZone(dateTimeZone).print(new DateTime());
    }

    public String clipboardText() {

    	Incident incident = incidentBean.getOpenIncident();

    	if (incident == null) {
    		return "";
    	}

    	StringBuilder builder = new StringBuilder();

    	builder.append(incident.getTitle());
    	builder.append("\n");
    	builder.append(incident.getDescription());
    	builder.append("\n");
    	for (ServiceGroupAlteration alteration : incident.getServiceGroupAlterations()) {
    		builder.append(new LongFormatter(alteration).format());
    		builder.append("\n");
    	}
    	return builder.toString();
    }
}
