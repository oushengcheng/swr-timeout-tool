package com.aps.wicc.web.formatter;

import java.util.List;

import com.aps.wicc.model.Alteration;
import com.aps.wicc.model.ServiceGroupAlteration;

class ShortFormatter {

    private ServiceGroupAlteration serviceGroupAlteration;
    private List<Alteration> alterations;
    private StringBuilder builder;

    public ShortFormatter(ServiceGroupAlteration serviceGroupAlteration) {
        this.serviceGroupAlteration = serviceGroupAlteration;
        this.alterations = serviceGroupAlteration.getAlterations();
    }

    public ShortFormatter(ServiceGroupAlteration serviceGroupAlteration, List<Alteration> alterations) {
        this.serviceGroupAlteration = serviceGroupAlteration;
        this.alterations = alterations;
    }

    public String format() {

        builder = new StringBuilder();

        if (isAffectDefined()) {
            addAffect();
        }

        if (isEffective()) {
            addEffective();
        }

        return builder.toString();

    }

    private boolean isAffectDefined() {
        return serviceGroupAlteration.getAffect() != null;
    }

    private void addAffect() {
        switch (serviceGroupAlteration.getAffect()) {
        case DELAYED:      delay();        break;
        case ALTERED:      altered();      break;
        case PARTRESTORED: partrestored(); break;
        case FULLRESTORED: fullrestored(); break;
        case CANCELLED:    cancelled();    break;
        case UNAFFECTED:   unaffacted();   break;
        }
    }

    private boolean isEffective() {
        return serviceGroupAlteration.getEffectiveFrom() != null && !serviceGroupAlteration.getEffectiveFrom().isEmpty();
    }

    private void addEffective() {
        builder.append(" ").append("effective").append(" ").append(serviceGroupAlteration.getEffectiveFrom());
    }

    private void delay() {
        builder.append(" ").append("are delayed");
        if (isDelayed()) {
            builder.append(" ").append(serviceGroupAlteration.getDelay());
        }
    }

    private boolean isDelayed() {
        return serviceGroupAlteration.getDelay() != null && !serviceGroupAlteration.getDelay().isEmpty();
    }

    private void altered() {
        builder.append(new AffectFormatter(alterations).format());
    }

    private void cancelled() {
        builder.append(" ").append("are cancelled");
    }

    private void unaffacted() {
        builder.append(" ").append("are unaffected");
    }

    private void partrestored() {
        builder.append(" ").append("are restored but");
        altered();
    }

    private void fullrestored() {
        builder.append(" ").append("are restored and will run as booked");
    }


}
