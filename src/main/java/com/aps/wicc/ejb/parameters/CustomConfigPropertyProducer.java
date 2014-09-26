package com.aps.wicc.ejb.parameters;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;

import org.apache.deltaspike.core.spi.config.BaseConfigPropertyProducer;

import com.aps.wicc.web.email.PublishDistributionList;

@ApplicationScoped
public class CustomConfigPropertyProducer extends BaseConfigPropertyProducer
{
    @Produces
    @Dependent
    @PlanViewIdParameter
    public String getViewIdParameter(final InjectionPoint injectionPoint) {
        return this.getStringPropertyValue(injectionPoint);
    }

    @Produces
    @Dependent
    @ScrollSpeed
    public String getScrollSpeed(final InjectionPoint injectionPoint) {
        return this.getStringPropertyValue(injectionPoint);
    }

    @Produces
    @Dependent
    @NotificationLower
    public Integer getNotificationLower(final InjectionPoint injectionPoint) {
        return Integer.valueOf(this.getStringPropertyValue(injectionPoint));
    }

    @Produces
    @Dependent
    @NotificationUpper
    public Integer getNotificationUpper(final InjectionPoint injectionPoint) {
        return Integer.valueOf(this.getStringPropertyValue(injectionPoint));
    }

    @Produces
    @Dependent
    @PublishDistributionList
    public String getPublishDistributionList(final InjectionPoint injectionPoint) {
        return this.getStringPropertyValue(injectionPoint);
    }
}
