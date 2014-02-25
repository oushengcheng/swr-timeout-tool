package com.aps.wicc.web.backing;

import java.io.*;

import javax.inject.*;

import org.omnifaces.cdi.*;

import com.aps.wicc.model.*;
import com.aps.wicc.ejb.*;

@Named
@ViewScoped
public class EditCloseBacking implements Serializable
{
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
