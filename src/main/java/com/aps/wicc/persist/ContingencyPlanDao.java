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

import com.aps.wicc.model.ContingencyPlan;
import com.aps.wicc.model.ContingencyPlan_;

public class ContingencyPlanDao
{
    private EntityManager entityManager;
    
    @Inject
    public ContingencyPlanDao(final EntityManager entityManager) {
        super();
        this.entityManager = entityManager;
    }
    
    public List<ContingencyPlan> findAll() {
        final CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();
        final CriteriaQuery<ContingencyPlan> criteria = builder.createQuery(ContingencyPlan.class);
        final Root<ContingencyPlan> contingencyPlanRoot = criteria.from(ContingencyPlan.class);
        
        criteria.select(contingencyPlanRoot);
        criteria.orderBy(builder.asc(contingencyPlanRoot.get(ContingencyPlan_.code)));
        
        final TypedQuery<ContingencyPlan> query = this.entityManager.createQuery(criteria);
        
        try {
            return (List<ContingencyPlan>)query.getResultList();
        }
        catch (NoResultException nre) {
            return Collections.emptyList();
        }
    }
    
    public ContingencyPlan findByCode(final String code) {
        final CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();
        final CriteriaQuery<ContingencyPlan> criteria = builder.createQuery(ContingencyPlan.class);
        final Root<ContingencyPlan> contingencyPlanRoot = criteria.from(ContingencyPlan.class);
        
        criteria.select(contingencyPlanRoot);
        criteria.where(builder.equal(contingencyPlanRoot.get(ContingencyPlan_.code), builder.literal(code)));
        
        final TypedQuery<ContingencyPlan> query = this.entityManager.createQuery(criteria);
        
        try {
            return (ContingencyPlan)query.getSingleResult();
        }
        catch (NoResultException nre) {        	           
            return null;
        }
    }
}
