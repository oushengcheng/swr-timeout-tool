package com.aps.wicc.util;

import java.util.logging.Logger;

import javax.annotation.Resource;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.sql.DataSource;

import org.joda.time.DateTimeZone;
import org.picketlink.annotations.PicketLink;

import com.aps.wicc.ejb.parameters.NotificationLower;
import com.aps.wicc.ejb.parameters.NotificationUpper;
import com.aps.wicc.ejb.parameters.PlanViewIdParameter;
import com.aps.wicc.ejb.parameters.ScrollSpeed;

public class Resources {

    @PersistenceContext
    private EntityManager em;

    @Resource(mappedName="java:jboss/wicc")
    private DataSource dataSource;

    @Inject
    @PlanViewIdParameter
    private String planViewIdParameter;

    @Inject
    @ScrollSpeed
    private String scrollSpeed;

    @Inject
    @NotificationLower
    private Integer notificationLower;

    @Inject
    @NotificationUpper
    private Integer notificationUpper;

    @Produces
    public Logger produceLog(final InjectionPoint injectionPoint) {
        return Logger.getLogger(injectionPoint.getMember().getDeclaringClass().getName());
    }

    @Produces
    @RequestScoped
    public FacesContext produceFacesContext() {
        return FacesContext.getCurrentInstance();
    }

    @Produces
    @PicketLink
    public EntityManager getPicketLinkEntityManager() {
        return this.em;
    }

    @Produces
    public EntityManager getManager() {
        return this.em;
    }

    @Produces
    public DataSource getDataSource() {
        return dataSource;
    }

    @Produces
    @Named
    public DateTimeZone getDateTimeZone() {
        return DateTimeZone.forID("Europe/London");
    }

    @Produces
    @Named
    public String getPlanViewIdParameter() {
        return this.planViewIdParameter;
    }

    @Produces
    @Named
    public String getScrollSpeed() {
        return this.scrollSpeed;
    }

    @Produces
    @Named
    public Integer getNotificationLower() {
        return this.notificationLower;
    }

    @Produces
    @Named
    public Integer getNotificationUpper() {
        return this.notificationUpper;
    }
}
