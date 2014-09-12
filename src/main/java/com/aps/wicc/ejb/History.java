package com.aps.wicc.ejb;

import org.joda.time.DateTime;

import com.aps.wicc.model.Affect;
import com.aps.wicc.model.Alteration;
import com.aps.wicc.model.AlterationType;
import com.aps.wicc.model.Direction;
import com.aps.wicc.model.Incident;
import com.aps.wicc.model.ServiceGroupAlteration;
import com.aps.wicc.model.Status;

public class History {

    private Long id;
    private Integer revision;
    private String title;
    private String description;
    private String footer;
    private DateTime created;
    private DateTime lastPublished;
    private DateTime nextReview;
    private Status status;
    private Long serviceGroupAlterationId;
    private String serviceGroup;
    private String departureTimes;
    private Direction direction;
    private Affect affect;
    private String effectiveFrom;
    private String delay;
    private String freeform;
    private AlterationType alterationType;
    private String location;

    public History(final Integer revision, final Incident incident, final ServiceGroupAlteration serviceGroupAlteration, final Alteration alteration) {
        super();
        this.id = incident.getId();
        this.revision = new Integer(revision);
        this.title = incident.getTitle();
        this.description = incident.getDescription();
        this.footer = incident.getFooter();
        this.created = incident.getCreated();
        this.lastPublished = incident.getLastPublished();
        this.nextReview = incident.getNextReview();
        this.status = incident.getStatus();
        this.serviceGroupAlterationId = serviceGroupAlteration.getId();
        this.serviceGroup = serviceGroupAlteration.getServiceGroup().getStandardDescription();
        this.departureTimes = serviceGroupAlteration.getDepartureTimes();
        this.direction = serviceGroupAlteration.getDirection();
        this.affect = serviceGroupAlteration.getAffect();
        this.effectiveFrom = serviceGroupAlteration.getEffectiveFrom();
        this.delay = serviceGroupAlteration.getDelay();
        this.freeform = serviceGroupAlteration.getFreeform();
        this.alterationType = alteration.getAlterationType();
        this.location = alteration.getLocation();
    }

    public History(final Integer revision, final Incident incident, final ServiceGroupAlteration serviceGroupAlteration) {
        super();
        this.id = incident.getId();
        this.revision = new Integer(revision);
        this.title = incident.getTitle();
        this.description = incident.getDescription();
        this.footer = incident.getFooter();
        this.created = incident.getCreated();
        this.lastPublished = incident.getLastPublished();
        this.nextReview = incident.getNextReview();
        this.status = incident.getStatus();
        this.serviceGroupAlterationId = serviceGroupAlteration.getId();
        this.serviceGroup = serviceGroupAlteration.getServiceGroup().getStandardDescription();
        this.departureTimes = serviceGroupAlteration.getDepartureTimes();
        this.direction = serviceGroupAlteration.getDirection();
        this.affect = serviceGroupAlteration.getAffect();
        this.effectiveFrom = serviceGroupAlteration.getEffectiveFrom();
        this.delay = serviceGroupAlteration.getDelay();
        this.freeform = serviceGroupAlteration.getFreeform();
        this.alterationType = null;
        this.location = null;
    }

    public History(final Integer revision, final Incident incident) {
        super();
        this.id = incident.getId();
        this.revision = new Integer(revision);
        this.title = incident.getTitle();
        this.description = incident.getDescription();
        this.footer = incident.getFooter();
        this.created = incident.getCreated();
        this.lastPublished = incident.getLastPublished();
        this.nextReview = incident.getNextReview();
        this.status = incident.getStatus();
        this.serviceGroupAlterationId = null;
        this.serviceGroup = null;
        this.departureTimes = null;
        this.direction = null;
        this.affect = null;
        this.effectiveFrom = null;
        this.delay = null;
        this.freeform = null;
        this.alterationType = null;
        this.location = null;
    }

    public Long getId() {
        return id;
    }

    public Integer getRevision() {
        return this.revision;
    }

    public String getTitle() {
        return this.title;
    }

    public String getDescription() {
        return this.description;
    }

    public String getFooter() {
        return footer;
    }

    public DateTime getCreated() {
        return this.created;
    }

    public DateTime getLastPublished() {
        return this.lastPublished;
    }

    public DateTime getNextReview() {
        return this.nextReview;
    }

    public Status getStatus() {
        return this.status;
    }

    public Long getServiceGroupAlterationId() {
        return serviceGroupAlterationId;
    }

    public String getServiceGroup() {
        return this.serviceGroup;
    }

    public String getDepartureTimes() {
        return departureTimes;
    }

    public Direction getDirection() {
        return this.direction;
    }

    public Affect getAffect() {
        return this.affect;
    }

    public String getEffectiveFrom() {
        return this.effectiveFrom;
    }

    public String getDelay() {
        return this.delay;
    }

    public String getFreeform() {
        return this.freeform;
    }

    public AlterationType getAlterationType() {
        return this.alterationType;
    }

    public String getLocation() {
        return this.location;
    }
}
