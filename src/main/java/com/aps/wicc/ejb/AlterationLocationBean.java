package com.aps.wicc.ejb;

import static com.google.common.collect.Sets.newHashSet;

import java.io.*;

import javax.ejb.*;
import javax.inject.Inject;

import com.aps.wicc.persist.*;
import com.aps.wicc.model.*;
import com.google.common.collect.*;

import java.util.*;

@Stateless
public class AlterationLocationBean implements Serializable
{
    private static final long serialVersionUID = 1L;
    private AlterationLocationDao alterationLocationDao;
    
    public AlterationLocationBean() {
        super();
    }
    
    @Inject
    public AlterationLocationBean(final AlterationLocationDao alterationLocationDao) {
        super();
        this.alterationLocationDao = alterationLocationDao;
    }
    
    public List<String> getLocations(final ServiceGroup serviceGroup, final Direction direction, final AlterationType alterationType) {
    	
    	if (alterationType == AlterationType.STARTFINISH) {
    	
    		Set<String> set1 = new HashSet<>(alterationLocationDao.getLocations(serviceGroup, newHashSet(Direction.FORWARD), AlterationType.NEWORIGIN));
    		Set<String> set2 = new HashSet<>(alterationLocationDao.getLocations(serviceGroup, newHashSet(Direction.REVERSE), AlterationType.NEWDEST));
    		
    		set1.addAll(set2);
    		
    		List<String> locations = Lists.newArrayList(set1);
    		
    		Collections.sort(locations);
    		
    		return locations;
    		
    		
    	} else if (alterationType == AlterationType.FINISHSTART) {
    		
    		Set<String> set1 = new HashSet<>(alterationLocationDao.getLocations(serviceGroup, newHashSet(Direction.REVERSE), AlterationType.NEWORIGIN));
    		Set<String> set2 = new HashSet<>(alterationLocationDao.getLocations(serviceGroup, newHashSet(Direction.FORWARD), AlterationType.NEWDEST));
    		
    		set1.addAll(set2);
    		
    		List<String> locations = Lists.newArrayList(set1);
    		
    		Collections.sort(locations);
    		
    		return locations;
    		
    	} if (direction == Direction.BOTH) {
        	
            return this.alterationLocationDao.getLocations(serviceGroup, newHashSet(Direction.REVERSE, Direction.FORWARD), alterationType);
            
        } else {
        
        	return this.alterationLocationDao.getLocations(serviceGroup, newHashSet(direction), alterationType);
        	
        }
    }
}
