package com.aps.wicc.ejb.initialisation;

import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.inject.Inject;
import javax.sql.DataSource;

import org.flywaydb.core.Flyway;

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
