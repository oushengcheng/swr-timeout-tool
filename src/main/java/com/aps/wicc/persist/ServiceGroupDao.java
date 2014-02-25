package com.aps.wicc.persist;

import java.util.Collections;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.aps.wicc.model.ServiceGroup;
import com.aps.wicc.model.ServiceGroup_;

public class ServiceGroupDao
{
    private EntityManager entityManager;
    
    @Inject
    public ServiceGroupDao(final EntityManager entityManager) {
        super();
        this.entityManager = entityManager;
    }
    
    public ServiceGroup findByUid(final String uid) {
        
    	final CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();
        final CriteriaQuery<ServiceGroup> criteria = builder.createQuery(ServiceGroup.class);
        final Root<ServiceGroup> serviceGroupRoot = criteria.from(ServiceGroup.class);
        
        criteria.select(serviceGroupRoot);
        criteria.where(builder.equal(serviceGroupRoot.get(ServiceGroup_.uid), (Object)uid));
        
        final TypedQuery<ServiceGroup> query = this.entityManager.createQuery(criteria);
        try {
            return (ServiceGroup)query.getSingleResult();
        }
        catch (NoResultException nre) {
            throw new RuntimeException(String.format("Unable to find ServiceGroup for uid %s", uid), nre);
        }
    }
    
    public List<ServiceGroup> findAll() {
        final CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();
        final CriteriaQuery<ServiceGroup> criteria = builder.createQuery(ServiceGroup.class);
        final Root<ServiceGroup> serviceGroupRoot = criteria.from(ServiceGroup.class);
        
        criteria.select(serviceGroupRoot);
        criteria.orderBy(builder.asc(serviceGroupRoot.get(ServiceGroup_.headcode)));
        final TypedQuery<ServiceGroup> query = this.entityManager.createQuery(criteria);
        
        try {
            return (List<ServiceGroup>)query.getResultList();
        }
        catch (NoResultException nre) {
            return Collections.emptyList();
        }
    }
}
