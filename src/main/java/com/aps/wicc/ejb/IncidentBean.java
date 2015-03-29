package com.aps.wicc.ejb;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.OptimisticLockException;
import javax.persistence.PersistenceException;

import org.joda.time.DateTime;
import org.omnifaces.util.Exceptions;

import com.aps.wicc.model.Incident;
import com.aps.wicc.model.Status;
import com.aps.wicc.persist.IncidentDao;

@Stateless
public class IncidentBean {

    private IncidentDao incidentDao;
    private EntityManager entityManager;

    private final static int NO_INCIDENTS = 5;

    @Inject
    public IncidentBean(IncidentDao incidentDao, EntityManager entityManager) {
        this.incidentDao = incidentDao;
        this.entityManager = entityManager;
    }

    public IncidentBean() {
    }

    public List<Incident> getIncidentList() {
        List<Incident> incidents = incidentDao.getOpenIncidents();
        incidents.addAll(incidentDao.getLastNClosedIncidents(NO_INCIDENTS));
        return incidents;
    }

    public List<Incident> getIncidentsByDateRange(final DateTime from, final DateTime until) {
        return this.incidentDao.getIncidents(from, until);
    }

    public Incident closeIncident(Incident incident) {
        incident = entityManager.merge(incident);
        incident.setStatus(Status.CLOSED);
        return incident;
    }

    public Incident newIncident(String title, String description, String footer) {
        Incident incident = new Incident();
        incident.setTitle(title);
        incident.setDescription(description);
        incident.setFooter(footer);
        return incident;
    }

    public Incident newIncident(String footer) {
    	return newIncident("", "", footer);
    }
    public Incident getOpenIncident() {
        return this.incidentDao.getOpenIncident();
    }

    public Incident save(Incident incident) {
        try {
            closeOpenIncidents();
            if (incident.getId() == null) {
                entityManager.persist(incident);
            } else {
                incident = entityManager.merge(incident);
            }
            incident.setLastPublished(new DateTime());
            incident.setStatus(Status.OPEN);
            return incident;
        } catch (PersistenceException pe) {
            if (Exceptions.is(pe, OptimisticLockException.class)) {
                throw new StaleDataException(pe.getCause());
            }
            throw pe;
        }
    }

    private void closeOpenIncidents() {
        for (Incident openincident : this.incidentDao.getOpenIncidents()) {
            openincident.setStatus(Status.CLOSED);
        }
    }

}
