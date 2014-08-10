package com.aps.wicc.ejb.initialisation;

import java.util.Iterator;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.enterprise.inject.Any;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;

@Startup
@Singleton
public class InitialisationBean {

    private Instance<Initialisable> initialisables;

    public InitialisationBean() {
    }

    @Inject
    public InitialisationBean(@Any Instance<Initialisable> initialisables) {
        this.initialisables = initialisables;
    }

    @PostConstruct
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    void atStartUp() {

        for (Iterator<Initialisable> iterator = initialisables.iterator(); iterator.hasNext(); ) {

            iterator.next().init();

        }

    }

}
