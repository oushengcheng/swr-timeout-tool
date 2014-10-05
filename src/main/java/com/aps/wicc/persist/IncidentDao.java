package com.aps.wicc.persist;

import java.util.Collections;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;

import org.joda.time.DateTime;

import com.aps.wicc.model.Incident;
import com.aps.wicc.model.Incident_;
import com.aps.wicc.model.Status;

public class IncidentDao {

    private EntityManager entityManager;
    private ServiceGroupAlterationDao serviceGroupAlterationDao;

    @Inject
    public IncidentDao(final EntityManager entityManager, final ServiceGroupAlterationDao serviceGroupAlterationDao) {
        super();
        this.entityManager = entityManager;
        this.serviceGroupAlterationDao = serviceGroupAlterationDao;
    }

    public List<Incident> getLastNClosedIncidents(int numberOfIncidents) {

        final CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();
        final CriteriaQuery<Incident> criteria = builder.createQuery(Incident.class);
        final Root<Incident> incidentRoot = criteria.from(Incident.class);

        criteria.select(incidentRoot);
        criteria.distinct(true);

        incidentRoot.fetch(Incident_.serviceGroupAlterations, JoinType.LEFT);

        criteria.where(builder.equal(incidentRoot.get(Incident_.status), builder.literal(Status.CLOSED)));
        criteria.orderBy(builder.desc(incidentRoot.get(Incident_.lastPublished)));

        final TypedQuery<Incident> query = this.entityManager.createQuery(criteria);
        query.setMaxResults(numberOfIncidents);

        try {

            final List<Incident> incidents = query.getResultList();
            this.serviceGroupAlterationDao.getServiceGroupAlterations(incidents);
            return incidents;

        } catch (NoResultException nre) {

            return Collections.emptyList();

        }
    }

    public List<Incident> getIncidents(final DateTime from, final DateTime until) {

        if (from == null || until == null) {
            return Collections.emptyList();
        }

        final CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();
        final CriteriaQuery<Incident> criteria = builder.createQuery(Incident.class);
        final Root<Incident> incidentRoot = criteria.from(Incident.class);

        criteria.select(incidentRoot);
        criteria.distinct(true);

        incidentRoot.fetch(Incident_.serviceGroupAlterations, JoinType.LEFT);

        criteria.where(builder.and(builder.greaterThanOrEqualTo(incidentRoot.get(Incident_.created), builder.literal(from)),
                                   builder.lessThan(incidentRoot.get(Incident_.created), builder.literal(until.plusDays(1)))));
        criteria.orderBy(builder.desc(incidentRoot.get(Incident_.created)));

        final TypedQuery<Incident> query = this.entityManager.createQuery(criteria);

        try {

            final List<Incident> incidents = query.getResultList();

            this.serviceGroupAlterationDao.getServiceGroupAlterations(incidents);

            return incidents;

        } catch (NoResultException nre) {

            return Collections.emptyList();
        }
    }

    public List<Incident> getOpenIncidents() {

        final CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();
        final CriteriaQuery<Incident> criteria = builder.createQuery(Incident.class);
        final Root<Incident> incidentRoot = criteria.from(Incident.class);

        criteria.select(incidentRoot);
        criteria.distinct(true);

        incidentRoot.fetch(Incident_.serviceGroupAlterations, JoinType.LEFT);

        criteria.where(builder.equal(incidentRoot.get(Incident_.status), builder.literal(Status.OPEN)));
        criteria.orderBy(builder.desc(incidentRoot.get(Incident_.lastPublished)));

        final TypedQuery<Incident> query = this.entityManager.createQuery(criteria);

        try {

            final List<Incident> incidents = query.getResultList();
            this.serviceGroupAlterationDao.getServiceGroupAlterations(incidents);
            return incidents;

        } catch (NoResultException nre) {
            return Collections.emptyList();
        }
    }

    public Incident getOpenIncident() {

        List<Incident> incidents = getOpenIncidents();

        if (incidents.size() > 0) {

            return incidents.get(0);

        } else {

            return null;

        }

    }

}
