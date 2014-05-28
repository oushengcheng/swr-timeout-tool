package com.aps.wicc.persist;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;

import com.aps.wicc.model.Incident;
import com.aps.wicc.model.ServiceGroupAlteration;
import com.aps.wicc.model.ServiceGroupAlteration_;

public class ServiceGroupAlterationDao
{
    private EntityManager entityManager;
    
    @Inject
    public ServiceGroupAlterationDao(final EntityManager entityManager) {
        super();
        this.entityManager = entityManager;
    }
    
    public List<ServiceGroupAlteration> getServiceGroupAlterations(final Collection<Incident> incidents) {
    	
        if (incidents.isEmpty()) {
        
        	return Collections.emptyList();
        
        }
        
        CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();
        CriteriaQuery<ServiceGroupAlteration> criteria = builder.createQuery(ServiceGroupAlteration.class);
        Root<ServiceGroupAlteration> root = criteria.from(ServiceGroupAlteration.class);
        
        criteria.distinct(true);
        
        final Join<ServiceGroupAlteration, Incident> incidentJoin = (Join<ServiceGroupAlteration, Incident>)root.join(ServiceGroupAlteration_.incident);        
        root.fetch(ServiceGroupAlteration_.alterations, JoinType.LEFT);
        
        criteria.select(root);
        criteria.distinct(true);
        criteria.where(incidentJoin.in(incidents));
        criteria.orderBy(builder.asc(root.get(ServiceGroupAlteration_.position)));
        
        final TypedQuery<ServiceGroupAlteration> query = this.entityManager.createQuery(criteria);
        try {
            return (List<ServiceGroupAlteration>)query.getResultList();
        }
        catch (NoResultException nre) {
            return Collections.emptyList();
        }
    }
}
