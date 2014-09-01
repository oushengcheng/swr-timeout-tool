package com.aps.wicc.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.deltaspike.core.api.exclude.Exclude;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.hibernate.envers.Audited;
import org.hibernate.validator.constraints.Length;
import org.joda.time.DateTime;

import com.google.common.collect.ImmutableList;

@Exclude
@Entity
@Audited
public class Incident
{

    @Id
    @GenericGenerator(name="hilogen", strategy="hilo")
    @GeneratedValue(strategy=GenerationType.TABLE, generator="hilogen")
    private Long id;

    @Version
    private Long version;

    @NotNull(message="{incident.titlenotnull}")
    @Length(max=50, message="{incident.titlelength}")
    private String title;

    @NotNull(message="{incident.descriptionnotnull}")
    @Type(type="text")
    private String description;

    @Type(type="org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    private DateTime created;

    @Type(type="org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    private DateTime lastPublished;

    @Type(type="org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    private DateTime nextReview;

    @Enumerated(EnumType.STRING)
    private Status status;

    @OneToMany(cascade=CascadeType.ALL)
    @OrderBy("position")
    private List<ServiceGroupAlteration> serviceGroupAlterations;

    public Incident() {
        super();
        this.serviceGroupAlterations = new ArrayList<ServiceGroupAlteration>();
        this.created = new DateTime();
        this.status = Status.OPEN;
    }

    public Long getId() {
        return this.id;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(final String title) {
        this.title = title;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    public DateTime getCreated() {
        return this.created;
    }

    public void setCreated(final DateTime created) {
        this.created = created;
    }

    public DateTime getLastPublished() {
        return this.lastPublished;
    }

    public void setLastPublished(final DateTime lastUpdated) {
        this.lastPublished = lastUpdated;
    }

    public DateTime getNextReview() {
        return this.nextReview;
    }

    public void setNextReview(final DateTime nextReview) {
        this.nextReview = nextReview;
    }

    public Status getStatus() {
        return this.status;
    }

    public void setStatus(final Status open) {
        this.status = open;
    }

    public List<ServiceGroupAlteration> getServiceGroupAlterations() {
        return ImmutableList.copyOf(this.serviceGroupAlterations);
    }

    public void setServiceGroupAlterations(final List<ServiceGroupAlteration> serviceGroupAlterations) {
        this.serviceGroupAlterations = new ArrayList<ServiceGroupAlteration>();
        for (final ServiceGroupAlteration serviceGroupAlteration : serviceGroupAlterations) {
            this.serviceGroupAlterations.add(serviceGroupAlteration);
            serviceGroupAlteration.setIncident(this);
            serviceGroupAlteration.setPosition(this.serviceGroupAlterations.size() - 1);
        }
    }

    public void addServiceGroupAlteration(final ServiceGroupAlteration alteration) {
        if (this.serviceGroupAlterations.contains(alteration)) {
            this.serviceGroupAlterations.get(this.serviceGroupAlterations.indexOf(alteration)).update(alteration);
        }
        else {
            this.serviceGroupAlterations.add(alteration);
            alteration.setIncident(this);
            alteration.setPosition(this.serviceGroupAlterations.size() - 1);
        }
    }

    public void removeServiceGroupAlteration(final ServiceGroupAlteration alteration) {
        for (int j = this.serviceGroupAlterations.indexOf(alteration); j < this.serviceGroupAlterations.size(); ++j) {
            this.serviceGroupAlterations.get(j).setPosition(j - 1);
        }
        this.serviceGroupAlterations.remove(alteration);
    }

    @Override
    public String toString() {
        final ToStringBuilder builder = new ToStringBuilder((Object)this);
        builder.append("id", (Object)this.id);
        builder.append("version", (Object)this.version);
        builder.append("title", (Object)this.title);
        builder.append("description", (Object)this.description);
        builder.append("created", (Object)this.created);
        builder.append("lastPublished", (Object)this.lastPublished);
        builder.append("nextReview", (Object)this.nextReview);
        builder.append("status", (Object)this.status);
        builder.append("serviceGroupAlterations", (Object)this.serviceGroupAlterations);
        return builder.toString();
    }
}
