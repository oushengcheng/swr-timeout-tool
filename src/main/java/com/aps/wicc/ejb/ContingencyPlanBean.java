package com.aps.wicc.ejb;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.aps.wicc.model.Alteration;
import com.aps.wicc.model.ContingencyAlteration;
import com.aps.wicc.model.ContingencyPlan;
import com.aps.wicc.model.Incident;
import com.aps.wicc.model.ServiceGroupAlteration;
import com.aps.wicc.persist.ContingencyPlanDao;

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

        contingencyPlan = this.entityManager.find(ContingencyPlan.class, contingencyPlan.getId());

        if (!contingencyPlan.getDescription().isEmpty()) {
        	incident.setDescription(incident.getDescription() + "\n" + contingencyPlan.getDescription());
        }

        for (ContingencyAlteration contingencyAlteration : contingencyPlan.getContingencyAlterations()) {

            ServiceGroupAlteration serviceGroupAlteration = new ServiceGroupAlteration();

            serviceGroupAlteration.setServiceGroup(contingencyAlteration.getServiceGroup());
            serviceGroupAlteration.setDirection(contingencyAlteration.getDirection());
            serviceGroupAlteration.setAffect(contingencyAlteration.getAffect());
            serviceGroupAlteration.setDelay(contingencyAlteration.getDelay());
            serviceGroupAlteration.setFreeform(contingencyAlteration.getFreeform());

            for (Alteration alteration : contingencyAlteration.getAlterations()) {

                serviceGroupAlteration.addAlteration(new Alteration(alteration));

            }

            incident.addServiceGroupAlteration(serviceGroupAlteration);
        }
    }
}
