package com.aps.wicc.ejb.initialisation;

import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.inject.Inject;
import javax.sql.DataSource;

import org.apache.deltaspike.core.api.exclude.Exclude;
import org.flywaydb.core.Flyway;

@Exclude
@Stateless
@TransactionManagement(TransactionManagementType.BEAN)
public class DbMigrationBean implements Initialisable {

    @Inject
    private DataSource dataSource;

    @Override
    public void init() {

        Flyway flyway = new Flyway();
        flyway.setDataSource(dataSource);
        flyway.migrate();

    }

}
