package com.aps.wicc.ejb.initialisation;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.aps.wicc.ejb.ContingencyPlanBean;
import com.aps.wicc.model.Affect;
import com.aps.wicc.model.Alteration;
import com.aps.wicc.model.AlterationType;
import com.aps.wicc.model.ContingencyAlteration;
import com.aps.wicc.model.ContingencyPlan;
import com.aps.wicc.model.Direction;
import com.aps.wicc.model.Incident;
import com.aps.wicc.model.ServiceGroupAlteration;
import com.aps.wicc.persist.ServiceGroupDao;
import com.aps.wicc.web.formatter.Functions;

@SeedingPhase(phaseNo=2)
public class ContingencyAlterationInitialisation implements Seedable {

    private EntityManager entityManager;
    private ServiceGroupDao serviceGroupDao;
    private ContingencyPlanBean contingencyPlanBean;
    private List<ContingencyPlan> plans = new ArrayList<>();

    @Inject
    public ContingencyAlterationInitialisation(EntityManager entityManager,
                                               ServiceGroupDao serviceGroupDao,
                                               ContingencyPlanBean contingencyPlanBean) {

        this.entityManager = entityManager;
        this.serviceGroupDao = serviceGroupDao;
        this.contingencyPlanBean = contingencyPlanBean;
    }

    @Override
    public void seed() {

        createPlans();
        savePlans();
        exportPlans();

    }

    private List<ContingencyPlan> createPlans() {

        ContingencyPlan contingencyPlan;

        contingencyPlan = new ContingencyPlan.PlanBuilder()
        .setCode("SRP01Y")
        .setTitle("SRP Yellow 1: Mainline 8tph")
        .add(new ContingencyAlteration.Builder()
            .setServiceGroup(this.serviceGroupDao.findByUid("1D"))
            .setDirection(Direction.BOTH)
            .setAffect(Affect.CANCELLED)
            .setDelay("")
            .setFreeform("")
            .build())
        .add(new ContingencyAlteration.Builder()
            .setServiceGroup(this.serviceGroupDao.findByUid("2M"))
            .setDirection(Direction.BOTH)
            .setAffect(Affect.CANCELLED)
            .setDelay("")
            .setFreeform("")
            .build())
        .add(new ContingencyAlteration.Builder()
            .setServiceGroup(this.serviceGroupDao.findByUid("2J"))
            .setDirection(Direction.BOTH)
            .setAffect(Affect.CANCELLED)
            .setDelay("")
            .setFreeform("")
            .build())
        .add(new ContingencyAlteration.Builder()
            .setServiceGroup(this.serviceGroupDao.findByUid("1L"))
            .setDirection(Direction.BOTH)
            .setAffect(Affect.ALTERED)
            .setDelay("")
            .setFreeform("")
                .addAlteration(new Alteration(AlterationType.STARTFINISH, "Basingstoke"))
            .build())
        .add(new ContingencyAlteration.Builder()
            .setServiceGroup(this.serviceGroupDao.findByUid("1W"))
            .setDirection(Direction.REVERSE)
            .setAffect(Affect.ALTERED)
            .setDelay("")
            .setFreeform("")
                .addAlteration(new Alteration(AlterationType.CALLADD, "Clapham Jn"))
                .addAlteration(new Alteration(AlterationType.CALLADD, "Woking"))
                .addAlteration(new Alteration(AlterationType.CALLADD, "Basingstoke"))
            .build())
        .add(new ContingencyAlteration.Builder()
            .setServiceGroup(this.serviceGroupDao.findByUid("2G"))
            .setDirection(Direction.BOTH)
            .setAffect(Affect.ALTERED)
            .setDelay("")
            .setFreeform("")
                .addAlteration(new Alteration(AlterationType.CALLADD, "Berrylands"))
            .build())
        .add(new ContingencyAlteration.Builder()
            .setServiceGroup(this.serviceGroupDao.findByUid("1T"))
            .setDirection(Direction.FORWARD)
            .setAffect(Affect.ALTERED)
            .setDelay("")
            .setFreeform("XX:09 departures retimed to XX:20")
                .addAlteration(new Alteration(AlterationType.NOTCALL, "Farnborough"))
                .addAlteration(new Alteration(AlterationType.NOTCALL, "Fleet"))
            .build())
        .build();
        plans.add(contingencyPlan);

    contingencyPlan = new ContingencyPlan.PlanBuilder()
        .setCode("SRP01R")
        .setTitle("SRP  Red 1: Mainline 12tph")
        .add(new ContingencyAlteration.Builder()
            .setServiceGroup(this.serviceGroupDao.findByUid("1D"))
            .setDirection(Direction.BOTH)
            .setAffect(Affect.CANCELLED)
            .setDelay("")
            .setFreeform("")
            .build())
        .add(new ContingencyAlteration.Builder()
            .setServiceGroup(this.serviceGroupDao.findByUid("2F"))
            .setDirection(Direction.BOTH)
            .setAffect(Affect.CANCELLED)
            .setDelay("")
            .setFreeform("")
            .build())
        .add(new ContingencyAlteration.Builder()
            .setServiceGroup(this.serviceGroupDao.findByUid("2J"))
            .setDirection(Direction.BOTH)
            .setAffect(Affect.CANCELLED)
            .setDelay("")
            .setFreeform("")
            .build())
        .add(new ContingencyAlteration.Builder()
            .setServiceGroup(this.serviceGroupDao.findByUid("2M"))
            .setDirection(Direction.BOTH)
            .setAffect(Affect.CANCELLED)
            .setDelay("")
            .setFreeform("")
            .build())
        .add(new ContingencyAlteration.Builder()
            .setServiceGroup(this.serviceGroupDao.findByUid("1L"))
            .setDirection(Direction.BOTH)
            .setAffect(Affect.ALTERED)
            .setDelay("")
            .setFreeform("")
                .addAlteration(new Alteration(AlterationType.STARTFINISH, "Basingstoke"))
            .build())
        .add(new ContingencyAlteration.Builder()
            .setServiceGroup(this.serviceGroupDao.findByUid("2H"))
            .setDirection(Direction.BOTH)
            .setAffect(Affect.ALTERED)
            .setDelay("")
            .setFreeform("")
                .addAlteration(new Alteration(AlterationType.RUNVIA, "Twickenham"))
                .addAlteration(new Alteration(AlterationType.CALLADD, "Strawberry Hill"))
            .build())
        .add(new ContingencyAlteration.Builder()
            .setServiceGroup(this.serviceGroupDao.findByUid("1P"))
            .setDirection(Direction.BOTH)
            .setAffect(Affect.ALTERED)
            .setDelay("")
            .setFreeform("")
                .addAlteration(new Alteration(AlterationType.STARTFINISH, "Woking"))
            .build())
        .add(new ContingencyAlteration.Builder()
            .setServiceGroup(this.serviceGroupDao.findByUid("1W"))
            .setDirection(Direction.REVERSE)
            .setAffect(Affect.ALTERED)
            .setDelay("")
            .setFreeform("")
                .addAlteration(new Alteration(AlterationType.CALLADD, "Clapham Jn"))
                .addAlteration(new Alteration(AlterationType.CALLADD, "Woking"))
                .addAlteration(new Alteration(AlterationType.CALLADD, "Basingstoke"))
            .build())
        .add(new ContingencyAlteration.Builder()
            .setServiceGroup(this.serviceGroupDao.findByUid("2G"))
            .setDirection(Direction.BOTH)
            .setAffect(Affect.ALTERED)
            .setDelay("")
            .setFreeform("")
                .addAlteration(new Alteration(AlterationType.CALLADD, "Berrylands"))
            .build())
        .add(new ContingencyAlteration.Builder()
            .setServiceGroup(this.serviceGroupDao.findByUid("1T"))
            .setDirection(Direction.FORWARD)
            .setAffect(Affect.ALTERED)
            .setDelay("")
            .setFreeform("XX:09 departures retimed to XX:20")
                .addAlteration(new Alteration(AlterationType.NOTCALL, "Farnborough"))
                .addAlteration(new Alteration(AlterationType.NOTCALL, "Fleet"))
            .build())
        .add(new ContingencyAlteration.Builder()
            .setServiceGroup(this.serviceGroupDao.findByUid("2L"))
            .setDirection(Direction.BOTH)
            .setAffect(Affect.ALTERED)
            .setDelay("")
            .setFreeform("")
                .addAlteration(new Alteration(AlterationType.CALLADD, "all stations Woking to Surbiton"))
            .build())
        .build();
        plans.add(contingencyPlan);

        return plans;

    }

    private void savePlans() {

        for (ContingencyPlan contingencyPlan : plans) {

            entityManager.persist(contingencyPlan);

        }
    }

    private void exportPlans() {

        Writer writer = null;

        try {

            writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("contingencyplans.txt"), "utf-8"));

            for (ContingencyPlan contingencyPlan : plans) {

                Incident incident = new Incident();

                incident.setTitle(contingencyPlan.getCode());
                incident.setDescription(contingencyPlan.getTitle());

                contingencyPlanBean.addContingencyPlanToIncident(incident, contingencyPlan);;

                writer.write("---" + "\n");
                writer.write(incident.getTitle() + "\n");
                writer.write(incident.getDescription() + "\n");

                for (ServiceGroupAlteration alteration : incident.getServiceGroupAlterations()) {

                    writer.write(Functions.format(alteration));

                    if (!alteration.getFreeform().isEmpty()) {

                        writer.write(" " + alteration.getFreeform());

                    }

                    writer.write("\n");
                }

                writer.write("---" + "\n");

            }

        } catch (IOException ex) {


        } finally {

           try {

               writer.close();

           } catch (Exception ex) {


           }
        }

    }
}
