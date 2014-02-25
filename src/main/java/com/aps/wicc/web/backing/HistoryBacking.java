package com.aps.wicc.web.backing;

import javax.inject.*;

import org.omnifaces.cdi.*;

import com.aps.wicc.ejb.*;

import org.joda.time.*;

import javax.annotation.*;

import java.util.*;

import com.aps.wicc.model.*;

import javax.faces.context.*;

import java.io.*;

@Named
@ViewScoped
public class HistoryBacking implements Serializable
{
    private static final long serialVersionUID = 1L;
    private IncidentBean incidentBean;
    private HistoryBean historyBean;
    private DateTimeZone dateTimeZone;
    private DateTime from;
    private DateTime until;
    
    public HistoryBacking() {
        super();
    }
    
    @Inject
    public HistoryBacking(final IncidentBean incidentBean, final HistoryBean historyBean, final DateTimeZone dateTimeZone) {
        super();
        this.incidentBean = incidentBean;
        this.historyBean = historyBean;
        this.dateTimeZone = dateTimeZone;
    }
    
    @PostConstruct
    private void init() {
        this.from = new DateTime(this.dateTimeZone).minusDays(28);
        this.until = new DateTime(this.dateTimeZone);
    }
    
    public List<Incident> getIncidents() {
        return this.incidentBean.getIncidents(this.from, this.until);
    }
    
    public DateTime getFrom() {
        return this.from;
    }
    
    public void setFrom(final DateTime from) {
        this.from = from;
    }
    
    public DateTime getUntil() {
        return this.until;
    }
    
    public void setUntil(final DateTime until) {
        this.until = until;
    }
    
    public String createReport() throws IOException {
        final FacesContext facesContext = FacesContext.getCurrentInstance();
        final ExternalContext externalContext = facesContext.getExternalContext();
        externalContext.setResponseContentType(this.historyBean.getFormat());
        externalContext.setResponseHeader("Content-Disposition", "attachment; filename=\"" + this.historyBean.getFilename() + "\"");
        this.historyBean.createReport(this.from, this.until, externalContext.getResponseOutputStream());
        facesContext.responseComplete();
        return null;
    }
}
