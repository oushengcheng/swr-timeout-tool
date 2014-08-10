package com.aps.wicc.ejb.parameters;

import java.lang.annotation.*;
import org.apache.deltaspike.core.api.config.*;
import javax.inject.*;

@Target({ ElementType.TYPE, ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
@ConfigProperty(name = "update.notification.lower")
@Qualifier
public @interface NotificationLower {
}
