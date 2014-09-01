package com.aps.wicc.web.backing.edit;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.event.AjaxBehaviorEvent;
import javax.inject.Inject;
import javax.inject.Named;

import org.joda.time.DateTimeZone;
import org.joda.time.LocalTime;
import org.omnifaces.cdi.ViewScoped;

import com.aps.wicc.ejb.AlterationLocationBean;
import com.aps.wicc.ejb.ServiceGroupBean;
import com.aps.wicc.model.Affect;
import com.aps.wicc.model.Alteration;
import com.aps.wicc.model.AlterationType;
import com.aps.wicc.model.Direction;
import com.aps.wicc.model.ServiceGroup;
import com.aps.wicc.model.ServiceGroupAlteration;
import com.aps.wicc.web.Pages;

@Named
@ViewScoped
public class EditServiceGroupBacking implements Serializable {

    private static final long serialVersionUID = 1L;

    private ServiceGroupBean serviceGroupBean;
    private AlterationLocationBean alterationLocationBean;
    private DateTimeZone dateTimeZone;
    private CurrentEdit currentEdit;

    private List<ServiceGroup> serviceGroups;

    private Alteration alteration = new Alteration();
    private List<Alteration> alterations;

    public EditServiceGroupBacking() {
    }

    @Inject
    public EditServiceGroupBacking(ServiceGroupBean serviceGroupBean,
                                   AlterationLocationBean alterationLocationBean,
                                   DateTimeZone dateTimeZone,
                                   CurrentEdit currentEdit) {
        this.serviceGroupBean = serviceGroupBean;
        this.alterationLocationBean = alterationLocationBean;
        this.dateTimeZone = dateTimeZone;
        this.currentEdit = currentEdit;
        this.alterations = new ArrayList<Alteration>(currentEdit.getServiceGroupAlteration().getAlterations());
    }

    @PostConstruct
    void init() {
        this.serviceGroups = this.serviceGroupBean.getServiceGroups();
    }

    public List<ServiceGroup> getServiceGroups() {
        return this.serviceGroups;
    }

    public Direction[] getDirections() {
        return Direction.values();
    }

    public Affect[] getAffects() {
        return Affect.values();
    }

    public AlterationType[] getAlterationTypes() {
        return AlterationType.values();
    }

    public ServiceGroupAlteration getServiceGroupAlteration() {
        return currentEdit.getServiceGroupAlteration();
    }

    public List<Alteration> getAlterations() {
        return alterations;
    }

//    public List<Alteration> getSortedAlterations() {
//        return new ArrayList<>();
//    }
//
//    public void setSortedAlterations(final List<Alteration> sortedAlterations) {
//        this.alterations = new ArrayList<>(sortedAlterations);
//    }

    public List<String> getLocations() {
        return this.alterationLocationBean.getLocations(getServiceGroupAlteration().getServiceGroup(),
                                                        getServiceGroupAlteration().getDirection(),
                                                        this.alteration.getAlterationType());
    }

    public Alteration getAlteration() {
        return this.alteration;
    }

    public void setAlteration(final Alteration alteration) {
        this.alteration = alteration;
    }

    public void delayValueChangeListener(final AjaxBehaviorEvent event) {
        getServiceGroupAlteration().setAffect(Affect.DELAYED);
    }

    public void setEffectiveNow() {
        getServiceGroupAlteration().setEffectiveFrom(effectiveNow());
    }

    private String effectiveNow() {
        return new StringBuilder("from ").append(new LocalTime(dateTimeZone).toString("HH:mm")).toString();
    }

    public void deleteFirstAlteration() {
        if (!alterations.isEmpty()) {
            alterations.remove(0);
        }
    }

    public void deleteLastAlteration() {
        if (!alterations.isEmpty()) {
            alterations.remove(alterations.size() - 1);
        }
    }

    public void addAlteration() {
        if (currentEdit.getServiceGroupAlteration().getAffect() != Affect.PARTRESTORED) {
            currentEdit.getServiceGroupAlteration().setAffect(Affect.ALTERED);
        }
        alterations.add(alteration);
        alteration = new Alteration();
    }

    // ========================== Save & Cancel =================================
    public Class<?> saveServiceGroupAlteration() {
        currentEdit.getServiceGroupAlteration().setAlterations(alterations);
        currentEdit.getIncident().addServiceGroupAlteration(currentEdit.getServiceGroupAlteration());
        return Pages.Editdetail.class;
    }

    public Class<?> cancelServiceGroupAlteration() {
        return Pages.Editdetail.class;
    }






}
