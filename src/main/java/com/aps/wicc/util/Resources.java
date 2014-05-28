package com.aps.wicc.util;

import javax.persistence.*;
import javax.enterprise.inject.spi.*;

import java.util.logging.*;

import javax.enterprise.inject.*;
import javax.faces.context.*;
import javax.enterprise.context.*;

import org.picketlink.annotations.*;
import org.joda.time.*;

import com.aps.wicc.ejb.initialisation.NotificationLower;
import com.aps.wicc.ejb.initialisation.NotificationUpper;
import com.aps.wicc.ejb.initialisation.PlanViewIdParameter;
import com.aps.wicc.ejb.initialisation.ScrollSpeed;

import javax.inject.*;

public class Resources
{
	@PersistenceContext
    private EntityManager em;
	
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
