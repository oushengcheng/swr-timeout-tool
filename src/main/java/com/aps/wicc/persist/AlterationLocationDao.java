package com.aps.wicc.persist;

import java.util.Collections;
import java.util.List;
import java.util.Set;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.aps.wicc.model.AlterationLocation;
import com.aps.wicc.model.AlterationLocation_;
import com.aps.wicc.model.AlterationType;
import com.aps.wicc.model.Direction;
import com.aps.wicc.model.ServiceGroup;

public class AlterationLocationDao
{
    private EntityManager entityManager;
    
    @Inject
    public AlterationLocationDao(final EntityManager entityManager) {
        super();
        this.entityManager = entityManager;
    }
    
    public List<String> getLocations(final ServiceGroup serviceGroup, final Set<Direction> directions, final AlterationType alterationType) {
    	
        final CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();
        final CriteriaQuery<String> criteria = builder.createQuery(String.class);
        final Root<AlterationLocation> serviceGroupRoot = criteria.from(AlterationLocation.class);
        
        criteria.select(serviceGroupRoot.get(AlterationLocation_.location));
        criteria.distinct(true);
        criteria.where(builder.and(builder.equal(serviceGroupRoot.get(AlterationLocation_.serviceGroup), serviceGroup), serviceGroupRoot.get(AlterationLocation_.direction).in(directions), builder.equal(serviceGroupRoot.get(AlterationLocation_.alterationType), alterationType)));
        criteria.orderBy(builder.asc(serviceGroupRoot.get(AlterationLocation_.location)));
        
        final TypedQuery<String> query = this.entityManager.createQuery(criteria);
        try {
            return (List<String>)query.getResultList();
        }
        catch (NoResultException nre) {
            return Collections.emptyList();
        }
    }
}
