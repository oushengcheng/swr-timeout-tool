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
    private Long orderingIndex;
    private String headcode;
    private Boolean unidirectional;
    private String forwardDescription;
    private String reverseDescription;
    private String bothDescription;
    
    @ManyToOne
    private ServiceGrouping serviceGrouping;
    
    public ServiceGroup() {
        super();
    }
    
    public ServiceGroup(Long orderingIndex, 
                        String uid, 
    		            String headcode, 
    		            Boolean unidirectional, 
    		            String forwardDescription,
    		            String reverseDescription,
    		            String bothDescription, 
    		            final ServiceGrouping serviceGrouping) {
        super();
        this.orderingIndex = orderingIndex;
        this.uid = uid;        
        this.headcode = headcode;
        this.unidirectional = unidirectional;
        this.forwardDescription = forwardDescription;
        this.reverseDescription = reverseDescription;
        this.bothDescription = bothDescription;
        this.serviceGrouping = serviceGrouping;
    }
    
    public Long getId() {
        return this.id;
    }
    
    public Long getOrderingIndex() {
		return orderingIndex;
	}
    
    public String getUid() {
        return this.uid;
    }
    
    public String getHeadcode() {
        return this.headcode;
    }

    public Boolean getUnidirectional() {
		return unidirectional;
	}

	public String getForwardDescription() {
		return forwardDescription;
	}

	public String getReverseDescription() {
		return reverseDescription;
	}

	public String getBothDescription() {
		return bothDescription;
	}

	public String getStandardDescription() {
		
		StringBuilder builder = new StringBuilder();
		
		if (headcode.length() > 0) {
			
			builder.append(headcode).append(": ");
			
		}
		
		if (unidirectional) {
			
			builder.append(bothDescription);
			
		} else {
			
			builder.append(forwardDescription);
		
		}
		
		return builder.toString();
		
	}
	
	public ServiceGrouping getServiceGrouping() {
        return this.serviceGrouping;
    }
    
    @Override
	public String toString() {
		ToStringBuilder builder = new ToStringBuilder(this);
		builder.append("id", id);
		builder.append("version", version);
		builder.append("uid", uid);
		builder.append("orderingIndex", orderingIndex);
		builder.append("headcode", headcode);
		builder.append("unidirectional", unidirectional);
		builder.append("forwardDescription", forwardDescription);
		builder.append("reverseDescription", reverseDescription);
		builder.append("bothDescription", bothDescription);
		builder.append("serviceGrouping", serviceGrouping);
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
