package com.aps.wicc.ejb;

import javax.ejb.*;
import javax.inject.Inject;

import com.aps.wicc.persist.*;

import javax.persistence.*;

import com.aps.wicc.model.*;

import java.util.*;

@Stateless
public class ContingencyPlanBean
{
    private ContingencyPlanDao contingencyPlanDao;
    private EntityManager entityManager;
    
    @Inject
    public ContingencyPlanBean(final ContingencyPlanDao contingencyPlanDao, final EntityManager entityManager) {
        super();
        this.contingencyPlanDao = contingencyPlanDao;
        this.entityManager = entityManager;
    }
    
    public ContingencyPlanBean() {
        super();
    }
    
    public List<ContingencyPlan> findAll() {
        return this.contingencyPlanDao.findAll();
    }
    
    public ContingencyPlan findByCode(final String code) {
        return this.contingencyPlanDao.findByCode(code);
    }
    
    public void addContingencyPlanToIncident(final Incident incident, ContingencyPlan contingencyPlan) {
        contingencyPlan = (ContingencyPlan)this.entityManager.find(ContingencyPlan.class, contingencyPlan.getId());
        for (final ContingencyAlteration contingencyAlteration : contingencyPlan.getContingencyAlterations()) {
            final ServiceGroupAlteration serviceGroupAlteration = new ServiceGroupAlteration();
            serviceGroupAlteration.setServiceGroup(contingencyAlteration.getServiceGroup());
            serviceGroupAlteration.setDirection(contingencyAlteration.getDirection());
            serviceGroupAlteration.setAffect(contingencyAlteration.getAffect());
            for (final Alteration alteration : contingencyAlteration.getAlterations()) {
                serviceGroupAlteration.addAlteration(new Alteration(alteration));
            }
            incident.addServiceGroupAlteration(serviceGroupAlteration);
        }
    }
}
