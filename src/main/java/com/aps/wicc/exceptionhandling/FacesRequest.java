package com.aps.wicc.exceptionhandling;

import javax.inject.*;
import java.lang.annotation.*;

@Qualifier
@Target({ ElementType.TYPE, ElementType.METHOD, ElementType.PARAMETER, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface FacesRequest {
}
