package com.aps.wicc.web.backing.edit;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.apache.deltaspike.core.api.config.view.ViewConfig;
import org.omnifaces.cdi.ViewScoped;

import com.aps.wicc.model.ServiceGroupAlteration;
import com.aps.wicc.web.Pages;

@Named
@ViewScoped
public class SortBacking implements Serializable {

    private static final long serialVersionUID = 1L;

    private CurrentEdit currentEdit;

    private List<ServiceGroupAlteration> serviceGroupAlterations = new ArrayList<>();

    public SortBacking() {
    }

    @Inject
    public SortBacking(CurrentEdit currentEdit) {
        this.currentEdit = currentEdit;
    }

    public List<ServiceGroupAlteration> getUnsortedServiceGroupAlterations() {
        return currentEdit.getIncident().getServiceGroupAlterations();
    }

    public List<ServiceGroupAlteration> getServiceGroupAlterations() {
        return serviceGroupAlterations;
    }

    public void setServiceGroupAlterations(final List<ServiceGroupAlteration> serviceGroupAlterations) {
        this.serviceGroupAlterations = new ArrayList<>(serviceGroupAlterations);
    }

    public Class<? extends ViewConfig> saveSortServiceGroupAlteration() {
        currentEdit.getIncident().setServiceGroupAlterations(serviceGroupAlterations);
        return Pages.Editdetail.class;
    }

    public Class<? extends ViewConfig> cancelSortServiceGroupAlteration() {
        return Pages.Editdetail.class;
    }


}
