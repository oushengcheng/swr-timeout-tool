package com.aps.wicc.model;

import org.apache.deltaspike.core.api.exclude.*;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.envers.*;
import org.apache.commons.lang3.builder.*;

@Exclude
@Entity
@Audited
public class ServiceGrouping
{
	@Id
	@GenericGenerator(name="hilogen", strategy="hilo")
	@GeneratedValue(strategy=GenerationType.TABLE, generator="hilogen")	
    private Long id;
	
	@Version
    private Long version;
	
    private Integer priority;
    private String name;
    
    public ServiceGrouping() {
        super();
    }
    
    public ServiceGrouping(final String name, final Integer priority) {
        super();
        this.name = name;
        this.priority = priority;
    }
    
    public String getName() {
        return this.name;
    }
    
    public Integer getPriority() {
        return this.priority;
    }
    
    @Override
    public String toString() {
        final ToStringBuilder builder = new ToStringBuilder((Object)this);
        builder.append("id", (Object)this.id);
        builder.append("version", (Object)this.version);
        builder.append("name", (Object)this.name);
        builder.append("priority", (Object)this.priority);
        return builder.toString();
    }
    
    @Override
    public int hashCode() {
        final HashCodeBuilder builder = new HashCodeBuilder();
        builder.append(this.name);
        builder.append(this.priority);
        return builder.toHashCode();
    }
    
    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ServiceGrouping)) {
            return false;
        }
        final ServiceGrouping that = (ServiceGrouping)obj;
        final EqualsBuilder builder = new EqualsBuilder();
        builder.append(this.name, that.name);
        builder.append(this.priority, that.priority);
        return builder.isEquals();
    }
}
