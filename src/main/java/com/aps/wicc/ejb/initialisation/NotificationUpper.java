package com.aps.wicc.ejb.initialisation;

import java.lang.annotation.*;
import org.apache.deltaspike.core.api.config.*;
import javax.inject.*;

@Target({ ElementType.TYPE, ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
@ConfigProperty(name = "update.notification.upper")
@Qualifier
public @interface NotificationUpper {
}