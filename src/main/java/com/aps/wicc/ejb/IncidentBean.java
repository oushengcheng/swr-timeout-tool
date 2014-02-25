package com.aps.wicc.ejb;

import javax.ejb.*;
import javax.inject.Inject;

import com.aps.wicc.persist.*;

import org.joda.time.*;

import com.aps.wicc.model.*;

import org.omnifaces.util.*;

import com.aps.wicc.ejb.exceptions.*;

import javax.persistence.*;

import java.util.*;

@Stateless
public class IncidentBean
{
    private IncidentDao incidentDao;
    private EntityManager entityManager;
    
    @Inject
    public IncidentBean(final IncidentDao incidentDao, final EntityManager entityManager) {
        super();
        this.incidentDao = incidentDao;
        this.entityManager = entityManager;
    }
    
    public IncidentBean() {
        super();
    }
    
    public List<Incident> getAllForPreviousWeek() {
        return this.incidentDao.getOpenAndPreviousWeeksIncidents();
    }
    
    public List<Incident> getIncidents(final DateTime from, final DateTime until) {
        return this.incidentDao.getIncidents(from, until);
    }
    
    public Incident closeIncident(Incident incident) {
        incident = (Incident)this.entityManager.merge((Object)incident);
        incident.setStatus(Status.CLOSED);
        return incident;
    }
    
    public Incident getOpenIncident() {
        return this.incidentDao.getOpenIncident();
    }
    
    public Incident save(Incident incident) {
        try {
            if (incident.getId() == null) {
                for (final Incident openincident : this.incidentDao.getOpenIncidents()) {
                    openincident.setStatus(Status.CLOSED);
                }
                this.entityManager.persist((Object)incident);
            }
            else {
                incident = (Incident)this.entityManager.merge((Object)incident);
            }
            incident.setLastPublished(new DateTime());
            incident.setStatus(Status.OPEN);
            return incident;
        }
        catch (PersistenceException pe) {
            if (Exceptions.is(pe, OptimisticLockException.class)) {
                throw new StaleDataException(pe.getCause());
            }
            throw pe;
        }
    }
}
