package com.aps.wicc.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import org.apache.commons.lang3.builder.*;

@Embeddable
public class Alteration
{	

	@Enumerated(EnumType.STRING)
    @NotNull(message="{alteration.alterationtype}")
	private AlterationType alterationType;
	
	@NotNull(message="{alteration.location}")
    private String location;
    
    public Alteration() {
        super();
    }
    
    public Alteration(final AlterationType alterationType, final String location) {
        super();
        this.alterationType = alterationType;
        this.location = location;
    }
    
    public Alteration(final Alteration alteration) {
        super();
        this.alterationType = alteration.alterationType;
        this.location = alteration.location;
    }
    
    public AlterationType getAlterationType() {
        return this.alterationType;
    }
    
    public void setAlterationType(final AlterationType alterationType) {
        this.alterationType = alterationType;
    }
    
    public String getLocation() {
        return this.location;
    }
    
    public void setLocation(final String location) {
        this.location = location;
    }
    
    @Override
    public String toString() {
        final ToStringBuilder builder = new ToStringBuilder((Object)this);
        builder.append("alterationType", (Object)this.alterationType);
        builder.append("location", (Object)this.location);
        return builder.toString();
    }
    
    @Override
    public int hashCode() {
        final HashCodeBuilder builder = new HashCodeBuilder();
        builder.append((Object)this.alterationType);
        builder.append((Object)this.location);
        return builder.toHashCode();
    }
    
    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Alteration)) {
            return false;
        }
        final Alteration that = (Alteration)obj;
        final EqualsBuilder builder = new EqualsBuilder();
        builder.append((Object)this.alterationType, (Object)that.alterationType);
        builder.append((Object)this.location, (Object)that.location);
        return builder.isEquals();
    }
}
