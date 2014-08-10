package com.aps.wicc.ejb.initialisation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.enterprise.util.AnnotationLiteral;
import javax.inject.Qualifier;

@Qualifier
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.TYPE, ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER })
@interface SeedingPhase {

    int phaseNo() default 1;

    public static class SeedingPhaseLiteral extends AnnotationLiteral<SeedingPhase> implements SeedingPhase {

        private static final long serialVersionUID = 1L;

        private int phaseNo;

        public SeedingPhaseLiteral(int phaseNo) {
            this.phaseNo = phaseNo;
        }

        @Override
        public int phaseNo() {
            return phaseNo;
        }

    }
}
