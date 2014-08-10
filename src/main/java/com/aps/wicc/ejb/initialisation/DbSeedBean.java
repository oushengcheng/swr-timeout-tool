package com.aps.wicc.ejb.initialisation;

import java.util.Iterator;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.apache.deltaspike.core.api.exclude.Exclude;

@Startup
@Singleton
@Exclude
public class DbSeedBean {

    @Inject
    @Primary
    private Instance<Initialisable> primary;

    @Inject
    @Secondary
    private Instance<Initialisable> secondary;

    @PostConstruct
    void atStartUp() {

        ToStringBuilder.setDefaultStyle(ToStringStyle.SHORT_PREFIX_STYLE);

        Iterator<Initialisable> iterator = this.primary.iterator();

        while (iterator.hasNext()) {
            iterator.next().init();
        }

        iterator = this.secondary.iterator();

        while (iterator.hasNext()) {
            iterator.next().init();
        }

    }

}
