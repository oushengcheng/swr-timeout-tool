package com.aps.wicc.ejb.initialisation;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@Startup
@Singleton
public class GlobalInitialisationBean {

    @PostConstruct
    void atStartUp() {

        ToStringBuilder.setDefaultStyle(ToStringStyle.SHORT_PREFIX_STYLE);

    }

}
