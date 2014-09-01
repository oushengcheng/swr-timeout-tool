package com.aps.wicc.web.backing.edit;

import java.io.Serializable;

import javax.inject.Inject;
import javax.inject.Named;

import org.omnifaces.cdi.ViewScoped;

import com.aps.wicc.ejb.IncidentBean;
import com.aps.wicc.model.Incident;

@Named
@ViewScoped
public class EditCloseBacking implements Serializable {

    private static final long serialVersionUID = 1L;
    private Incident incident;
    private IncidentBean incidentBean;

    public EditCloseBacking() {
        super();
    }

    @Inject
    public EditCloseBacking(final IncidentBean incidentBean) {
        super();
        this.incidentBean = incidentBean;
    }

    public void setClose(final Incident incident) {
        this.incident = incident;
    }

    public void doClose() {
        this.incidentBean.closeIncident(this.incident);
    }
}
