package com.aps.wicc.web.backing.edit;

import java.io.Serializable;

import javax.ejb.EJBException;
import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.constraints.NotNull;

import org.apache.deltaspike.core.api.config.view.ViewConfig;
import org.apache.deltaspike.jsf.api.message.JsfMessage;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.LocalTime;
import org.joda.time.MutableDateTime;
import org.omnifaces.cdi.ViewScoped;
import org.omnifaces.util.Exceptions;

import com.aps.wicc.ejb.IncidentBean;
import com.aps.wicc.ejb.Sorter;
import com.aps.wicc.ejb.exceptions.StaleDataException;
import com.aps.wicc.model.Incident;
import com.aps.wicc.model.ServiceGroupAlteration;
import com.aps.wicc.web.Messages;
import com.aps.wicc.web.Pages;

@Named
@ViewScoped
public class EditDetailBacking implements Serializable {

    private static final long serialVersionUID = 1L;

    private IncidentBean incidentBean;
    private DateTimeZone dateTimeZone;
    private Sorter sorter;
    private JsfMessage<Messages> messages;
    private CurrentEdit currentEdit;

    @NotNull(message="{editsummarybacking_nextreview_notnull}")
    private LocalTime nextReview;

    public EditDetailBacking() {
    }

    @Inject
    @SuppressWarnings("cdi-ambiguous-dependency")
    public EditDetailBacking(IncidentBean incidentBean,
                             DateTimeZone dateTimeZone,
                             Sorter sorter,
                             JsfMessage<Messages> messages,
                             CurrentEdit editingIncident) {
        this.incidentBean = incidentBean;
        this.dateTimeZone = dateTimeZone;
        this.sorter = sorter;
        this.messages = messages;
        this.currentEdit = editingIncident;
    }

    public Incident getEditIncident() {
        return this.currentEdit.getIncident();
    }

    public LocalTime getNextReview() {
        return this.nextReview;
    }

    public void setNextReview(final LocalTime nextReview) {
        this.nextReview = nextReview;
    }

    public void setNextReviewPlus(final Integer plus) {
        this.nextReview = new LocalTime(this.dateTimeZone).plusMinutes(plus);
    }

    // ================= Command Button Bar Actions ===================================
    public Class<?> newServiceGroupAlteration() {
        currentEdit.setServiceGroupAlteration(new ServiceGroupAlteration());
        return Pages.Editservicegroup.class;
    }

    public Class<?> selectContingencyPlan() {
        return Pages.Contingency.class;
    }

    public Class<? extends ViewConfig> startSortServiceGroupAlteration() {
        return Pages.Sort.class;
    }

    public void autoSort() {
        getEditIncident().setServiceGroupAlterations(this.sorter.sort(getEditIncident().getServiceGroupAlterations()));
    }

    // ================= Service Group Alteration Actions ===================================
    public ServiceGroupAlteration getServiceGroupAlteration() {
        return currentEdit.getServiceGroupAlteration();
    }

    public void setServiceGroupAlteration(final ServiceGroupAlteration serviceGroupAlteration) {
        currentEdit.setServiceGroupAlteration(serviceGroupAlteration);
    }

    public Class<?> editServiceGroupAlteration(final ServiceGroupAlteration serviceGroupAlteration) {
        currentEdit.setServiceGroupAlteration(serviceGroupAlteration.fullCopy());
        return Pages.Editservicegroup.class;
    }

    public Class<?> copyServiceGroupAlteration(final ServiceGroupAlteration serviceGroupAlteration) {
        currentEdit.setServiceGroupAlteration(serviceGroupAlteration.partialCopy());
        return Pages.Editservicegroup.class;
    }

    public void deleteServiceGroupAlteration() {
        getEditIncident().removeServiceGroupAlteration(currentEdit.getServiceGroupAlteration());
    }

    // ================= Save & Cancel ===================================
    public Class<?> saveIncident() {

        final MutableDateTime now = new LocalTime().isAfter(this.nextReview) ? new DateTime().plusDays(1).toMutableDateTime(this.dateTimeZone) : new DateTime().toMutableDateTime(this.dateTimeZone);

        now.setHourOfDay(this.nextReview.getHourOfDay());
        now.setMinuteOfHour(this.nextReview.getMinuteOfHour());

        getEditIncident().setNextReview(now.toDateTime());

        try {

            this.incidentBean.save(getEditIncident());
            return Pages.Editsummary.class;

        } catch (EJBException e) {

            if (Exceptions.is(e, StaleDataException.class)) {
                this.messages.addError().staleData();
                return null;
            }
            throw e;
        }
    }

    public Class<?> cancelIncident() {
        return Pages.Editsummary.class;
    }





}