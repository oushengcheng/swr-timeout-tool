package com.aps.wicc.model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.deltaspike.core.api.exclude.Exclude;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.IndexColumn;
import org.hibernate.envers.Audited;
import org.hibernate.validator.constraints.Length;

import com.google.common.collect.ImmutableList;

@Exclude
@Entity
@Audited
public class ServiceGroupAlteration
{
			
	@Id
	@GenericGenerator(name="hilogen", strategy="hilo")
	@GeneratedValue(strategy=GenerationType.TABLE, generator="hilogen")	
    private Long id;
	
	@Version
    private Long version;
	
    private String uuid;
    private int position;
    
    @ManyToOne
    private Incident incident;
    
    @ManyToOne
    @NotNull(message="{servicegroupalteration.servicegroup}")
    private ServiceGroup serviceGroup;
    
    @Enumerated(EnumType.STRING)
    @NotNull(message="{servicegroupalteration.direction}")
    private Direction direction;
    
    @Enumerated(EnumType.STRING)
    @NotNull(message="{servicegroupalteration.affect}")
    private Affect affect;
    
    @Length(max=100, message="{servicegroupalteration.effectivelength}")
    private String effectiveFrom;
    
    @Length(max=50, message="{servicegroupalteration.delaylength}")
    private String delay;
    
    @Length(max=100, message="{servicegroupalteration.freeformlength}")
    private String freeform;
    
    @ElementCollection
    @IndexColumn(name="position")
    private List<Alteration> alterations;
    
    public ServiceGroupAlteration() {
        super();
        this.alterations = new ArrayList<Alteration>();
        this.uuid = UUID.randomUUID().toString();
    }
    
    private ServiceGroupAlteration(final Boolean fullCopy, final ServiceGroupAlteration serviceGroupAlteration) {
        this();
        if (fullCopy) {
            this.id = serviceGroupAlteration.getId();
            this.version = serviceGroupAlteration.getVersion();
            this.uuid = serviceGroupAlteration.getUuid();
            this.position = serviceGroupAlteration.getPosition();
            this.incident = serviceGroupAlteration.getIncident();
        }
        this.update(serviceGroupAlteration);
    }
    
    void update(final ServiceGroupAlteration serviceGroupAlteration) {
        this.serviceGroup = serviceGroupAlteration.getServiceGroup();
        this.direction = serviceGroupAlteration.getDirection();
        this.affect = serviceGroupAlteration.getAffect();
        this.effectiveFrom = serviceGroupAlteration.getEffectiveFrom();
        this.delay = serviceGroupAlteration.getDelay();
        this.freeform = serviceGroupAlteration.getFreeform();
        this.alterations = new ArrayList<Alteration>();
        for (final Alteration alteration : serviceGroupAlteration.getAlterations()) {
            this.alterations.add(new Alteration(alteration));
        }
    }
    
    public ServiceGroupAlteration fullCopy() {
        return new ServiceGroupAlteration(true, this);
    }
    
    public ServiceGroupAlteration partialCopy() {
        return new ServiceGroupAlteration(false, this);
    }
    
    public Long getId() {
        return this.id;
    }
    
    Long getVersion() {
        return this.version;
    }
    
    public String getUuid() {
        return this.uuid;
    }
    
    public int getPosition() {
        return this.position;
    }
    
    void setPosition(final int position) {
        this.position = position;
    }
    
    public Incident getIncident() {
        return this.incident;
    }
    
    void setIncident(final Incident incident) {
        this.incident = incident;
    }
    
    public ServiceGroup getServiceGroup() {
        return this.serviceGroup;
    }
    
    public void setServiceGroup(final ServiceGroup serviceGroup) {
        this.serviceGroup = serviceGroup;
    }
    
    public Direction getDirection() {
        return this.direction;
    }
    
    public void setDirection(final Direction direction) {
        this.direction = direction;
    }
    
    public Affect getAffect() {
        return this.affect;
    }
    
    public void setAffect(final Affect affect) {
        this.affect = affect;
    }
    
    public String getEffectiveFrom() {
        return this.effectiveFrom;
    }
    
    public void setEffectiveFrom(final String effectiveFrom) {
        this.effectiveFrom = effectiveFrom;
    }
    
    public String getDelay() {
        return this.delay;
    }
    
    public void setDelay(final String delay) {
        this.delay = delay;
    }
    
    public String getFreeform() {
        return this.freeform;
    }
    
    public void setFreeform(final String freeform) {
        this.freeform = freeform;
    }
    
    public List<Alteration> getAlterations() {
        return ImmutableList.copyOf(this.alterations);
    }
    
    public void setAlterations(final List<Alteration> alterations) {
        this.alterations = new ArrayList<Alteration>();
        for (final Alteration alteration : alterations) {
            this.alterations.add(new Alteration(alteration));
        }
    }
    
    public void addAlteration(final Alteration alteration) {
        this.alterations.add(alteration);
    }
    
    public void removeAlteration(final Alteration alteration) {
        this.alterations.remove(alteration);
    }
    
    public void removeAlteration(final int index) {
        this.alterations.remove(index);
    }
    
    @Override
    public String toString() {
        final ToStringBuilder builder = new ToStringBuilder((Object)this);
        builder.append("id", this.id);
        builder.append("version", this.version);
        builder.append("uuid", this.uuid);
        builder.append("position", this.position);
        builder.append("incident", this.incident);
        builder.append("serviceGroup", this.serviceGroup);
        builder.append("direction", this.direction);
        builder.append("affect", this.affect);
        builder.append("effectiveFrom", this.effectiveFrom);
        builder.append("delay", this.delay);
        builder.append("freeform", this.freeform);
        builder.append("alterations", this.alterations);
        return builder.toString();
    }
    
    @Override
    public int hashCode() {
        final HashCodeBuilder builder = new HashCodeBuilder();
        builder.append(this.uuid);
        return builder.toHashCode();
    }
    
    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ServiceGroupAlteration)) {
            return false;
        }
        final ServiceGroupAlteration that = (ServiceGroupAlteration)obj;
        final EqualsBuilder builder = new EqualsBuilder();
        builder.append(this.uuid, that.uuid);
        return builder.isEquals();
    }
}
