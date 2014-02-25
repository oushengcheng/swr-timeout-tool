package com.aps.wicc.model;

import org.apache.deltaspike.core.api.exclude.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import org.apache.commons.lang3.builder.*;
import org.hibernate.annotations.GenericGenerator;

@Exclude
@Entity
public class AlterationLocation
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
    @NotNull
    private Direction direction;
    
    @Enumerated(EnumType.STRING)
    @NotNull
    private AlterationType alterationType;
    
    @NotNull
    private String location;
    
    public AlterationLocation() {
        super();
    }
    
    public AlterationLocation(final ServiceGroup serviceGroup, final Direction direction, final AlterationType alterationType, final String location) {
        super();
        this.serviceGroup = serviceGroup;
        this.direction = direction;
        this.alterationType = alterationType;
        this.location = location;
    }
    
    public ServiceGroup getServiceGroup() {
        return this.serviceGroup;
    }
    
    public Direction getDirection() {
        return this.direction;
    }
    
    public AlterationType getAltertionType() {
        return this.alterationType;
    }
    
    public String getLocation() {
        return this.location;
    }
    
    @Override
    public String toString() {
        final ToStringBuilder builder = new ToStringBuilder((Object)this);
        builder.append("id", (Object)this.id);
        builder.append("version", (Object)this.version);
        builder.append("serviceGroup", (Object)this.serviceGroup);
        builder.append("direction", (Object)this.direction);
        builder.append("altertionType", (Object)this.alterationType);
        builder.append("location", (Object)this.location);
        return builder.toString();
    }
}
