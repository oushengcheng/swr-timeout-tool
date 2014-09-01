package com.aps.wicc.web.backing.edit;

import java.io.Serializable;

import org.apache.deltaspike.core.api.scope.WindowScoped;

import com.aps.wicc.model.Incident;
import com.aps.wicc.model.ServiceGroupAlteration;

@WindowScoped
public class CurrentEdit implements Serializable {

    private static final long serialVersionUID = 1L;

    private Incident incident;
    private ServiceGroupAlteration serviceGroupAlteration;

    public Incident getIncident() {
        return incident;
    }

    public void setIncident(Incident incident) {
        this.incident = incident;
    }

    public ServiceGroupAlteration getServiceGroupAlteration() {
        return serviceGroupAlteration;
    }

    public void setServiceGroupAlteration(ServiceGroupAlteration serviceGroupAlteration) {
        this.serviceGroupAlteration = serviceGroupAlteration;
    }

}
