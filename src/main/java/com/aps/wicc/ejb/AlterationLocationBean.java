package com.aps.wicc.ejb;

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
        if (direction == Direction.BOTH) {
            return this.alterationLocationDao.getLocations(serviceGroup, Sets.newHashSet(new Direction[] { Direction.UP, Direction.DOWN }), alterationType);
        }
        return this.alterationLocationDao.getLocations(serviceGroup, Sets.newHashSet(new Direction[] { direction }), alterationType);
    }
}
