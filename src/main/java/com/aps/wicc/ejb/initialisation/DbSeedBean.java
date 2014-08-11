package com.aps.wicc.ejb.initialisation;

import java.util.Iterator;

import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.enterprise.inject.Any;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;

import org.apache.deltaspike.core.api.exclude.Exclude;

import com.aps.wicc.ejb.initialisation.SeedingPhase.SeedingPhaseLiteral;

@Stateless
@Exclude
@TransactionManagement(TransactionManagementType.CONTAINER)
public class DbSeedBean implements Initialisable {

    private Instance<Seedable> seedable;

    public DbSeedBean() {
    }

    @Inject
    public DbSeedBean(@Any Instance<Seedable> seedable) {
        this.seedable = seedable;
    }

    @Override
    public void init() {

        int phaseNo = 1;

        do {

            Instance<Seedable> instances = getSeedables(phaseNo);

            doSeeding(instances);

            phaseNo++;

        } while (seedablesExist(phaseNo));

    }

    private Instance<Seedable> getSeedables(int phaseNo) {
        return seedable.select(seedingPhase(phaseNo));
    }

    private SeedingPhaseLiteral seedingPhase(int phaseNo) {
        return new SeedingPhaseLiteral(phaseNo);
    }

    private void doSeeding(Instance<Seedable> instances) {
        for (Iterator<Seedable> iterator = instances.iterator(); iterator.hasNext(); ) {
            iterator.next().seed();
        }
    }

    private boolean seedablesExist(int phaseNo) {
        return !getSeedables(phaseNo).isUnsatisfied();
    }
}
