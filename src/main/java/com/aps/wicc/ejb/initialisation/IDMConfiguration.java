package com.aps.wicc.ejb.initialisation;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;

import org.picketlink.idm.config.IdentityConfiguration;
import org.picketlink.idm.config.IdentityConfigurationBuilder;
import org.picketlink.idm.jpa.model.sample.simple.AccountTypeEntity;
import org.picketlink.idm.jpa.model.sample.simple.AttributeTypeEntity;
import org.picketlink.idm.jpa.model.sample.simple.GroupTypeEntity;
import org.picketlink.idm.jpa.model.sample.simple.IdentityTypeEntity;
import org.picketlink.idm.jpa.model.sample.simple.PartitionTypeEntity;
import org.picketlink.idm.jpa.model.sample.simple.PasswordCredentialTypeEntity;
import org.picketlink.idm.jpa.model.sample.simple.RelationshipIdentityTypeEntity;
import org.picketlink.idm.jpa.model.sample.simple.RelationshipTypeEntity;
import org.picketlink.idm.jpa.model.sample.simple.RoleTypeEntity;
import org.picketlink.idm.model.Relationship;
import org.picketlink.internal.EEJPAContextInitializer;

@ApplicationScoped
public class IDMConfiguration {

    @Inject
    private EEJPAContextInitializer contextInitializer;

    private IdentityConfiguration identityConfig;

    public IDMConfiguration() {
        super();
        this.identityConfig = null;
    }

    @Produces
    IdentityConfiguration createConfig() {
        if (this.identityConfig == null) {
            this.initConfig();
        }
        return this.identityConfig;
    }

    @SuppressWarnings("unchecked")
    private void initConfig() {
        final IdentityConfigurationBuilder builder = new IdentityConfigurationBuilder();
        builder.named("default")
                .stores()
                .jpa()
                .mappedEntity(
                    AccountTypeEntity.class,
                    RoleTypeEntity.class,
                    GroupTypeEntity.class,
                    IdentityTypeEntity.class,
                    RelationshipTypeEntity.class,
                    RelationshipIdentityTypeEntity.class,
                    PartitionTypeEntity.class,
                    PasswordCredentialTypeEntity.class,
                    AttributeTypeEntity.class)
                .supportGlobalRelationship(Relationship.class)
                .addContextInitializer(this.contextInitializer)
                .supportAllFeatures();
        this.identityConfig = builder.build();
    }
}
