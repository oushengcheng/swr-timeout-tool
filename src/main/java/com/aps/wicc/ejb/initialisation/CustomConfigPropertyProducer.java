package com.aps.wicc.ejb.initialisation;

import org.apache.deltaspike.core.spi.config.*;
import javax.enterprise.inject.spi.*;
import javax.enterprise.inject.*;
import javax.enterprise.context.*;

@ApplicationScoped
public class CustomConfigPropertyProducer extends BaseConfigPropertyProducer
{
    @Produces
    @Dependent
    @PlanViewIdParameter
    public String getViewIdParameter(final InjectionPoint injectionPoint) {
        return this.getStringPropertyValue(injectionPoint);
    }
}
