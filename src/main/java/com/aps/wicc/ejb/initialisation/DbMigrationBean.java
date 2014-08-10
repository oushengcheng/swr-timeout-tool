package com.aps.wicc.ejb.initialisation;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.inject.Inject;
import javax.sql.DataSource;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.flywaydb.core.Flyway;

@Startup
@Singleton
@TransactionManagement(TransactionManagementType.BEAN)
public class DbMigrationBean {

    @Inject
    private DataSource dataSource;

    @PostConstruct
    void atStartUp() {

        ToStringBuilder.setDefaultStyle(ToStringStyle.SHORT_PREFIX_STYLE);

        Flyway flyway = new Flyway();
        flyway.setDataSource(dataSource);
        flyway.migrate();

    }

}
