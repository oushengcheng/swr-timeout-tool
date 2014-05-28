package com.aps.wicc.model;

import org.apache.deltaspike.core.api.exclude.*;

import javax.persistence.*;

import com.google.common.collect.*;

import java.util.*;

import org.apache.commons.lang3.builder.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.IndexColumn;

@Exclude
@Entity
public class ContingencyAlteration
{
	@Id
	@GenericGenerator(name="hilogen", strategy="hilo")
	@GeneratedValue(strategy=GenerationType.TABLE, generator="hilogen")	
    private Long id;
	
	@Version
    private Long version;
	
	@ManyToOne
    private ServiceGroup serviceGroup;
    
    @Enumerated(EnumType.STRING)
    private Direction direction;
    
    @Enumerated(EnumType.STRING)
    private Affect affect;
    
    private String delay;
    
    private String freeform;
    
    @ElementCollection
    @IndexColumn(name="position")
    private List<Alteration> alterations;
    
    public ContingencyAlteration() {
        super();
        this.alterations = new ArrayList<Alteration>();
    }
    
    private ContingencyAlteration(ServiceGroup serviceGroup, Direction direction, Affect affect, String delay, String freeform, List<Alteration> alterations) {
        super();
        this.alterations = new ArrayList<Alteration>();
        this.serviceGroup = serviceGroup;
        this.direction = direction;
        this.affect = affect;
        this.delay = delay;
        this.freeform = freeform;
        this.alterations = new ArrayList<Alteration>();
        for (final Alteration alteration : alterations) {
            this.alterations.add(new Alteration(alteration));
        }
    }
    
    public Long getId() {
        return this.id;
    }
    
    public ServiceGroup getServiceGroup() {
        return this.serviceGroup;
    }
    
    public Direction getDirection() {
        return this.direction;
    }
    
    public Affect getAffect() {
        return this.affect;
    }
    
    public String getDelay() {
		return delay;
	}

	public String getFreeform() {
		return freeform;
	}
	
    public List<Alteration> getAlterations() {
        return ImmutableList.copyOf(this.alterations);
    }
    
    @Override
    public String toString() {
        final ToStringBuilder builder = new ToStringBuilder((Object)this);
        builder.append("id", this.id);
        builder.append("version", this.version);
        builder.append("serviceGroup", this.serviceGroup);
        builder.append("direction", this.direction);
        builder.append("affect", this.affect);
        builder.append("delay", this.delay);
        builder.append("freeform", this.freeform);
        builder.append("alterations", this.alterations);
        return builder.toString();
    }
    
    public static class Builder
    {
        private ServiceGroup serviceGroup;
        private Direction direction;
        private Affect affect;
        private String delay;        
        private String freeform;
        private List<Alteration> alterations;
        
        public Builder() {
            super();
            this.alterations = new ArrayList<Alteration>();
        }
        
        public Builder setServiceGroup(ServiceGroup serviceGroup) {
            this.serviceGroup = serviceGroup;
            return this;
        }
        
        public Builder setDirection(Direction direction) {
            this.direction = direction;
            return this;
        }
        
        public Builder setAffect(Affect affect) {
            this.affect = affect;
            return this;
        }
        
        public Builder setDelay(String delay) {
            this.delay = delay;
            return this;
        }
        
        public Builder setFreeform(String freeform) {
            this.freeform = freeform;
            return this;
        }
        
        public Builder addAlteration(final Alteration alteration) {
            this.alterations.add(alteration);
            return this;
        }
        
        public ContingencyAlteration build() {
            return new ContingencyAlteration(serviceGroup, direction, affect, delay, freeform, alterations);
        }
    }

	
}
