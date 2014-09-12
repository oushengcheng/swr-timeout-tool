package com.aps.wicc.web.formatter;

import java.util.List;

import com.aps.wicc.model.Alteration;
import com.aps.wicc.model.Direction;
import com.aps.wicc.model.ServiceGroupAlteration;

public class LongFormatter {

    private ServiceGroupAlteration serviceGroupAlteration;
    private List<Alteration> alterations;
    private StringBuilder builder;

    public LongFormatter(ServiceGroupAlteration serviceGroupAlteration) {
        this.serviceGroupAlteration = serviceGroupAlteration;
        this.alterations = serviceGroupAlteration.getAlterations();
    }

    public LongFormatter(ServiceGroupAlteration serviceGroupAlteration, List<Alteration> alterations) {
        this.serviceGroupAlteration = serviceGroupAlteration;
        this.alterations = alterations;
    }

    public String format() {

        builder = new StringBuilder();

        addHeadcode();
        addDepartureTimes();
        addDirection();
        addServicesText();
        addShortFormat();

        return builder.toString();

    }

    private void addHeadcode() {
        if (isHeadcodeDefined()) {
            builder.append(serviceGroupAlteration.getServiceGroup().getHeadcode());
            builder.append(": ");
        }
    }

    private boolean isHeadcodeDefined() {
        return serviceGroupAlteration.getServiceGroup() != null
                  && !serviceGroupAlteration.getServiceGroup().getHeadcode().isEmpty();
    }

    private void addDepartureTimes() {
        if (isDepartureTimeDefined()) {
            builder.append(serviceGroupAlteration.getDepartureTimes());
            builder.append(" ");
        }
    }

    private boolean isDepartureTimeDefined() {
        return serviceGroupAlteration.getServiceGroup() != null
                && serviceGroupAlteration.getDepartureTimes() != null
                  && !serviceGroupAlteration.getDepartureTimes().isEmpty();
    }

    private void addDirection() {

        if (isDirectionDefined()) {

            if (isUseBothDescription()) {

                builder.append(serviceGroupAlteration.getServiceGroup().getBothDescription());

            } else if (isUseForwardDescription()) {

                builder.append(serviceGroupAlteration.getServiceGroup().getForwardDescription());

            } else {

                builder.append(serviceGroupAlteration.getServiceGroup().getReverseDescription());

            }

        }
    }

    private boolean isDirectionDefined() {
        return serviceGroupAlteration.getServiceGroup() != null;
    }

    private boolean isUseBothDescription() {
        return serviceGroupAlteration.getServiceGroup().getUnidirectional()
                                   || serviceGroupAlteration.getDirection() == Direction.BOTH;
    }

    private boolean isUseForwardDescription() {
        return serviceGroupAlteration.getDirection() == Direction.FORWARD;
    }

    private void addServicesText() {
        if (builder.length() > 0) {
            builder.append(" ").append("services");
        }
    }

    private void addShortFormat() {

        ShortFormatter formatter = new ShortFormatter(serviceGroupAlteration, alterations);

        builder.append(formatter.format());

    }
}
