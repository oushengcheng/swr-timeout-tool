package com.aps.wicc.model;

import org.apache.deltaspike.core.api.exclude.*;

import javax.persistence.*;
import javax.persistence.Entity;

import org.hibernate.annotations.*;
import org.hibernate.envers.*;
import org.apache.commons.lang3.builder.*;

@Exclude
@Entity
@Immutable
@Audited
public class ServiceGroup
{
	@Id
	@GenericGenerator(name="hilogen", strategy="hilo")
	@GeneratedValue(strategy=GenerationType.TABLE, generator="hilogen")	
    private Long id;
	
	@Version
    private Long version;
	
    private String uid;
    private String headcode;
    private String description;
    
    @ManyToOne
    private ServiceGrouping serviceGrouping;
    
    public ServiceGroup() {
        super();
    }
    
    public ServiceGroup(final String uid, final String headcode, final String description, final ServiceGrouping serviceGrouping) {
        super();
        this.uid = uid;
        this.headcode = headcode;
        this.description = description;
        this.serviceGrouping = serviceGrouping;
    }
    
    public Long getId() {
        return this.id;
    }
    
    public String getUid() {
        return this.uid;
    }
    
    public String getHeadcode() {
        return this.headcode;
    }
    
    public String getDescription() {
        return this.description;
    }
    
    public ServiceGrouping getServiceGrouping() {
        return this.serviceGrouping;
    }
    
    @Override
    public String toString() {
        final ToStringBuilder builder = new ToStringBuilder((Object)this);
        builder.append("id", (Object)this.id);
        builder.append("version", (Object)this.version);
        builder.append("headcode", (Object)this.headcode);
        builder.append("description", (Object)this.description);
        builder.append("serviceGrouping", (Object)this.serviceGrouping);
        return builder.toString();
    }
    
    @Override
    public int hashCode() {
        final HashCodeBuilder builder = new HashCodeBuilder();
        builder.append(this.uid);
        return builder.toHashCode();
    }
    
    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ServiceGroup)) {
            return false;
        }
        final ServiceGroup that = (ServiceGroup)obj;
        final EqualsBuilder builder = new EqualsBuilder();
        builder.append(this.uid, that.uid);
        return builder.isEquals();
    }
}
