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
    
    @ElementCollection
    @IndexColumn(name="position")
    private List<Alteration> alterations;
    
    public ContingencyAlteration() {
        super();
        this.alterations = new ArrayList<Alteration>();
    }
    
    private ContingencyAlteration(final ServiceGroup serviceGroup, final Direction direction, final Affect affect, final List<Alteration> alterations) {
        super();
        this.alterations = new ArrayList<Alteration>();
        this.serviceGroup = serviceGroup;
        this.direction = direction;
        this.affect = affect;
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
    
    public List<Alteration> getAlterations() {
        return ImmutableList.copyOf(this.alterations);
    }
    
    @Override
    public String toString() {
        final ToStringBuilder builder = new ToStringBuilder((Object)this);
        builder.append("id", (Object)this.id);
        builder.append("version", (Object)this.version);
        builder.append("serviceGroup", (Object)this.serviceGroup);
        builder.append("direction", (Object)this.direction);
        builder.append("affect", (Object)this.affect);
        builder.append("alterations", (Object)this.alterations);
        return builder.toString();
    }
    
    public static class Builder
    {
        private ServiceGroup serviceGroup;
        private Direction direction;
        private Affect affect;
        private List<Alteration> alterations;
        
        public Builder() {
            super();
            this.alterations = new ArrayList<Alteration>();
        }
        
        public Builder setServiceGroup(final ServiceGroup serviceGroup) {
            this.serviceGroup = serviceGroup;
            return this;
        }
        
        public Builder setDirection(final Direction direction) {
            this.direction = direction;
            return this;
        }
        
        public Builder setAffect(final Affect affect) {
            this.affect = affect;
            return this;
        }
        
        public Builder addAlteration(final Alteration alteration) {
            this.alterations.add(alteration);
            return this;
        }
        
        public ContingencyAlteration build() {
            return new ContingencyAlteration(this.serviceGroup, this.direction, this.affect, this.alterations);
        }
    }
}
