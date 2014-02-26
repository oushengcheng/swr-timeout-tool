package com.aps.wicc.ejb.initialisation;

import org.apache.deltaspike.core.api.exclude.*;
import org.apache.deltaspike.core.api.projectstage.*;

import javax.inject.Inject;
import javax.persistence.*;

import com.aps.wicc.persist.*;

import org.joda.time.*;

import com.aps.wicc.model.*;

@Exclude(ifProjectStage = { ProjectStage.Production.class })
@Secondary
public class IncidentInitialisation implements Initialisable
{
	@Inject
    private EntityManager entityManager;
	
	@Inject
    private ServiceGroupDao serviceGroupDao;
    
    @Override
    public void init() {
        
    	Incident incident = new Incident();
        incident.setTitle("Fatality at Wimbledon");
        incident.setDescription("A person has been struck by a train at Wimbledon");
        incident.setCreated(new DateTime(2014, 2, 25, 10, 12, 30));
        incident.setLastPublished(new DateTime(2014, 2, 25, 10, 20, 30));
        incident.setStatus(Status.OPEN);
        
        final ServiceGroupAlteration alteration = new ServiceGroupAlteration();
        alteration.setServiceGroup(this.serviceGroupDao.findByUid("1A"));
        alteration.setDirection(Direction.BOTH);
        alteration.setAffect(Affect.ALTERED);
        alteration.addAlteration(new Alteration(AlterationType.NEWORIGIN, "Woking"));
        alteration.addAlteration(new Alteration(AlterationType.RUNVIA, "Cobham"));
        incident.addServiceGroupAlteration(alteration);
        this.entityManager.persist(incident);
        
        incident = new Incident();
        incident.setTitle("Signalling Problems");
        incident.setDescription("Total Loss of Signalling at Clapham Jn");
        incident.setCreated(new DateTime(2014, 2, 22, 10, 2, 30));
        incident.setLastPublished(new DateTime(2014, 2, 22, 19, 4, 30));
        incident.setStatus(Status.CLOSED);
        this.entityManager.persist(incident);
        
        incident = new Incident();
        incident.setTitle("Broken Rail");
        incident.setDescription("Broken Rail at Vauxhall");
        incident.setCreated(new DateTime(2014, 2, 22, 15, 2, 30));
        incident.setLastPublished(new DateTime(2014, 2, 22, 18, 34, 27));
        incident.setStatus(Status.CLOSED);        
        this.entityManager.persist(incident);
        
        incident = new Incident();
        incident.setTitle("Failed Train");
        incident.setDescription("Failed Train at Wimbledon");
        incident.setCreated(new DateTime(2014, 2, 1, 15, 2, 30));
        incident.setLastPublished(new DateTime(2014, 2, 1, 18, 34, 27));
        incident.setStatus(Status.OPEN);
        this.entityManager.persist(incident);
    }
}
