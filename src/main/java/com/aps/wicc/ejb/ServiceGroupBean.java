package com.aps.wicc.ejb;

import javax.ejb.*;
import javax.inject.Inject;

import com.aps.wicc.persist.*;

import java.util.*;

import com.aps.wicc.model.*;

@Stateless
public class ServiceGroupBean
{
    private ServiceGroupDao serviceGroupDao;
    
    @Inject
    public ServiceGroupBean(final ServiceGroupDao serviceGroupDao) {
        super();
        this.serviceGroupDao = serviceGroupDao;
    }
    
    public ServiceGroupBean() {
        super();
    }
    
    public List<ServiceGroup> getServiceGroups() {
        return this.serviceGroupDao.findAll();
    }
}
