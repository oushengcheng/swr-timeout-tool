package com.aps.wicc.web.backing;

import java.io.*;

import javax.inject.*;
import javax.validation.constraints.NotNull;

import com.aps.wicc.ejb.*;

import org.apache.deltaspike.jsf.api.message.*;

import javax.enterprise.context.*;

import java.util.*;

import javax.annotation.*;

import com.aps.wicc.model.*;

import javax.faces.event.*;

import org.apache.deltaspike.core.api.config.view.*;

import com.aps.wicc.web.*;
import com.aps.wicc.web.Messages;
import com.aps.wicc.ejb.exceptions.*;

import org.omnifaces.util.*;

import javax.ejb.*;

import org.joda.time.*;

@Named
@ConversationScoped
public class EditSummaryBacking implements Serializable
{

    private static final long serialVersionUID = 1L;
    private IncidentBean incidentBean;
    private ServiceGroupBean serviceGroupBean;
    private AlterationLocationBean alterationLocationBean;
    private ContingencyPlanBean contingencyPlanBean;
    private Sorter sorter;
    private JsfMessage<Messages> messages;
    private DateTimeZone dateTimeZone;
    private List<ServiceGroup> serviceGroups;
    private List<ContingencyPlan> contingencyPlans;
    private Conversation conversation;
    private Incident editIncident;
    private ServiceGroupAlteration editServiceGroupAlteration;
    
    @NotNull(message="{editsummarybacking_nextreview_notnull}")
    private LocalTime nextReview;
    private Alteration editAlteration;
    private List<Alteration> sortedAlterations;
    private boolean sortedAlterationsChanged;
    private List<ServiceGroupAlteration> sortedServiceGroupAlterations;
    
    @NotNull(message="{editsummarybacking_contingencyplan_notnull}")
    private ContingencyPlan contingencyPlan;
    
    public EditSummaryBacking() {
        super();
        this.serviceGroups = new ArrayList<ServiceGroup>();
        this.contingencyPlans = new ArrayList<ContingencyPlan>();
        this.editServiceGroupAlteration = new ServiceGroupAlteration();
        this.nextReview = null;
        this.editAlteration = new Alteration();
        this.sortedAlterations = new ArrayList<Alteration>();
        this.sortedAlterationsChanged = false;
        this.sortedServiceGroupAlterations = new ArrayList<ServiceGroupAlteration>();
    }
    
    @Inject
    public EditSummaryBacking(final IncidentBean incidentBean, final ServiceGroupBean serviceGroupBean, final AlterationLocationBean alterationLocationBean, final ContingencyPlanBean contingencyPlanBean, final Sorter sorter, final JsfMessage<Messages> messages, final DateTimeZone dateTimeZone, final Conversation conversation) {
        super();
        this.serviceGroups = new ArrayList<ServiceGroup>();
        this.contingencyPlans = new ArrayList<ContingencyPlan>();
        this.editServiceGroupAlteration = new ServiceGroupAlteration();
        this.nextReview = null;
        this.editAlteration = new Alteration();
        this.sortedAlterations = new ArrayList<Alteration>();
        this.sortedAlterationsChanged = false;
        this.sortedServiceGroupAlterations = new ArrayList<ServiceGroupAlteration>();
        this.incidentBean = incidentBean;
        this.serviceGroupBean = serviceGroupBean;
        this.alterationLocationBean = alterationLocationBean;
        this.contingencyPlanBean = contingencyPlanBean;
        this.sorter = sorter;
        this.messages = messages;
        this.dateTimeZone = dateTimeZone;
        this.conversation = conversation;
    }
    
    @PostConstruct
    void init() {
        this.serviceGroups = this.serviceGroupBean.getServiceGroups();
        this.contingencyPlans = this.contingencyPlanBean.findAll();
    }
    
    // ========================== Edit Detail Modal ==========================================
    
    public List<ServiceGroup> getServiceGroups() {
        return this.serviceGroups;
    }
    
    public List<String> getLocations() {
        return this.alterationLocationBean.getLocations(this.editServiceGroupAlteration.getServiceGroup(), this.editServiceGroupAlteration.getDirection(), this.editAlteration.getAlterationType());
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
    
    public Alteration getAlteration() {
        return this.editAlteration;
    }
    
    public void setAlteration(final Alteration alteration) {
        this.editAlteration = alteration;
    }
    
    public List<Alteration> getSortedAlterations() {
        return this.sortedAlterations;
    }
    
    public void setSortedAlterations(final List<Alteration> sortedAlterations) {
        this.sortedAlterations = sortedAlterations;
    }
    
    public void saveServiceGroupAlteration() {
        if (this.sortedAlterationsChanged) {
            this.editServiceGroupAlteration.setAlterations(this.sortedAlterations);
        }
        this.editIncident.addServiceGroupAlteration(this.editServiceGroupAlteration);
    }
    
    public void orderingValueChangeListener() {
        this.sortedAlterationsChanged = true;
    }
    
    public void delayValueChangeListener(final AjaxBehaviorEvent event) {
        this.editServiceGroupAlteration.setAffect(Affect.DELAYED);
    }
    
    public void setEffectiveNow() {
    	this.editServiceGroupAlteration.setEffectiveFrom(new StringBuilder("from ").append(new LocalTime(dateTimeZone).toString("HH:mm")).toString());
    }
    
    public void deleteFirstAlteration() {
        if (this.sortedAlterationsChanged && !this.sortedAlterations.isEmpty()) {
            this.sortedAlterations.remove(0);
            this.editServiceGroupAlteration.setAlterations(this.sortedAlterations);
            this.sortedAlterations = new ArrayList<Alteration>();
            this.sortedAlterationsChanged = false;
        }
        else if (!this.editServiceGroupAlteration.getAlterations().isEmpty()) {
            this.editServiceGroupAlteration.removeAlteration(this.editServiceGroupAlteration.getAlterations().get(0));
        }
    }
    
    public void deleteLastAlteration() {
        if (this.sortedAlterationsChanged && !this.sortedAlterations.isEmpty()) {
            this.sortedAlterations.remove(this.sortedAlterations.size() - 1);
            this.editServiceGroupAlteration.setAlterations(this.sortedAlterations);
            this.sortedAlterations = new ArrayList<Alteration>();
            this.sortedAlterationsChanged = false;
        }
        else if (!this.editServiceGroupAlteration.getAlterations().isEmpty()) {
            this.editServiceGroupAlteration.removeAlteration(this.editServiceGroupAlteration.getAlterations().size() - 1);
        }
    }
    
    public void addAlteration() {
        this.editServiceGroupAlteration.setAffect(Affect.ALTERED);
        this.editServiceGroupAlteration.addAlteration(this.editAlteration);
        this.editAlteration = new Alteration();
        this.sortedAlterations = new ArrayList<Alteration>();
        this.sortedAlterationsChanged = false;
    }
    
    // ========================== Edit Summary ==========================================
    
    public List<Incident> getIncidents() {
        return this.incidentBean.getAllForPreviousWeek();
    }
    
    public Class<? extends ViewConfig> newIncident() {
        this.editIncident = new Incident();
        this.conversation.begin();
        return Pages.Editdetail.class;
    }
    
    public Class<? extends ViewConfig> viewPlanStatic() {
        return Pages.Planview.class;
    }
    
    public Class<? extends ViewConfig> viewPlanScroll() {
        return Pages.Planviewscroll.class;
    }
    
    public Class<? extends ViewConfig> editIncident(final Incident incident) {
        this.editIncident = incident;
        this.conversation.begin();        
        return Pages.Editdetail.class;
    }
    
    public List<ServiceGroupAlteration> getSortedServiceGroupAlterations() {
        return this.sortedServiceGroupAlterations;
    }
    
    public void setSortedServiceGroupAlterations(final List<ServiceGroupAlteration> sortedServiceGroupAlterations) {
        this.sortedServiceGroupAlterations = sortedServiceGroupAlterations;
    }
    
    public Class<? extends ViewConfig> saveSortServiceGroupAlteration() {
        this.editIncident.setServiceGroupAlterations(this.sortedServiceGroupAlterations);
        return Pages.Editdetail.class;
    }
    
    public Class<? extends ViewConfig> startSortServiceGroupAlteration() {
        this.sortedServiceGroupAlterations = new ArrayList<ServiceGroupAlteration>();
        return Pages.Sort.class;
    }
    
    public Class<? extends ViewConfig> cancelSortServiceGroupAlteration() {
        return Pages.Editdetail.class;
    }
    
    // ========================== Edit Detail ========================================== 
    public Incident getEditIncident() {
        return this.editIncident;
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
    
    public void autoSort() {
        this.editIncident.setServiceGroupAlterations(this.sorter.sort(this.editIncident.getServiceGroupAlterations()));
    }
    
    public ServiceGroupAlteration getServiceGroupAlteration() {
        return this.editServiceGroupAlteration;
    }
    
    public void setServiceGroupAlteration(final ServiceGroupAlteration serviceGroupAlteration) {
        this.editServiceGroupAlteration = serviceGroupAlteration;
        this.sortedAlterations = new ArrayList<Alteration>();
        this.sortedAlterationsChanged = false;
    }
    
    public void editServiceGroupAlteration(final ServiceGroupAlteration serviceGroupAlteration) {
        this.editServiceGroupAlteration = serviceGroupAlteration.fullCopy();
        this.sortedAlterations = new ArrayList<Alteration>();
        this.sortedAlterationsChanged = false;
    }
    
    public void copyServiceGroupAlteration(final ServiceGroupAlteration serviceGroupAlteration) {
        this.editServiceGroupAlteration = serviceGroupAlteration.partialCopy();
        this.sortedAlterations = new ArrayList<Alteration>();
        this.sortedAlterationsChanged = false;
    }
    
    public void newServiceGroupAlteration() {
        this.editServiceGroupAlteration = new ServiceGroupAlteration();
        this.sortedAlterations = new ArrayList<Alteration>();
        this.sortedAlterationsChanged = false;
    }
    
    public void deleteServiceGroupAlteration() {
        this.editIncident.removeServiceGroupAlteration(this.editServiceGroupAlteration);
    }
    
    public Class<?> saveIncident() {
        
    	final MutableDateTime now = new LocalTime().isAfter(this.nextReview) ? new DateTime().plusDays(1).toMutableDateTime(this.dateTimeZone) : new DateTime().toMutableDateTime(this.dateTimeZone);
        
        now.setHourOfDay(this.nextReview.getHourOfDay());
        now.setMinuteOfHour(this.nextReview.getMinuteOfHour());
        
        this.editIncident.setNextReview(now.toDateTime());
        
        try {
            this.incidentBean.save(this.editIncident);
            this.conversation.end();
            return Pages.Editsummary.class;
        }
        catch (EJBException e) {
            if (Exceptions.is(e, StaleDataException.class)) {
                ((Messages)this.messages.addError()).staleData();
                return null;
            }
            throw e;
        }
    }
    
    public Class<?> cancelIncident() {
        this.conversation.end();
        return Pages.Editsummary.class;
    }
    
    // =============== Contingency Plan ==============================
    public Class<?> selectContingencyPlan() {
        return Pages.Contingency.class;
    }
    
    public ContingencyPlan getContingencyPlan() {
        return this.contingencyPlan;
    }
    
    public void setContingencyPlan(final ContingencyPlan contingencyPlan) {
    	System.out.println("Setting contingency plan");
        this.contingencyPlan = contingencyPlan;
    }
    
    public List<ContingencyPlan> getContingencyPlans() {
        return this.contingencyPlans;
    }
    
    public Class<? extends ViewConfig> addContingencyPlan() {    	
        this.contingencyPlanBean.addContingencyPlanToIncident(this.editIncident, this.contingencyPlan);
        return Pages.Editdetail.class;
    }
    
    public Class<? extends ViewConfig> cancelContingencyPlan() {
        return Pages.Editdetail.class;
    }
}
