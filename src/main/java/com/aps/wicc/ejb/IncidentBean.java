package com.aps.wicc.ejb;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.OptimisticLockException;
import javax.persistence.PersistenceException;

import org.joda.time.DateTime;
import org.omnifaces.util.Exceptions;

import com.aps.wicc.ejb.exceptions.StaleDataException;
import com.aps.wicc.model.Incident;
import com.aps.wicc.model.Status;
import com.aps.wicc.persist.IncidentDao;

@Stateless
public class IncidentBean {

    private IncidentDao incidentDao;
    private EntityManager entityManager;

    @Inject
    public IncidentBean(IncidentDao incidentDao, EntityManager entityManager) {
        this.incidentDao = incidentDao;
        this.entityManager = entityManager;
    }

    public IncidentBean() {
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

    public Incident newIncident(String footer) {
        Incident incident = new Incident();
        incident.setFooter(footer);
        return incident;
    }

    public Incident getOpenIncident() {
        return this.incidentDao.getOpenIncident();
    }

    public Incident save(Incident incident) {
        try {
            if (incident.getId() == null) {
                closeOpenIncidents();
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
