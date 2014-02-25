package com.aps.wicc.ejb.initialisation;

import org.apache.deltaspike.core.api.exclude.*;
import org.apache.deltaspike.core.api.projectstage.*;

import javax.inject.Inject;
import javax.persistence.*;

import com.aps.wicc.persist.*;
import com.aps.wicc.model.*;

@Exclude(ifProjectStage = { ProjectStage.Production.class })
@Secondary
public class ContingencyAlterationInitialisation implements Initialisable
{
	@Inject
    private EntityManager entityManager;
	
	@Inject
    private ServiceGroupDao serviceGroupDao;
    
    @Override
    public void init() {
        ContingencyPlan contingencyPlan = new ContingencyPlan.PlanBuilder()
        			.setCode("SRP1")
        			.setTitle("14tph reduction Raynes Park-Waterloo")
        			.add(new ContingencyAlteration.Builder()
        				.setServiceGroup(this.serviceGroupDao.findByUid("2L-B"))
        				.setDirection(Direction.DOWN)
        				.setAffect(Affect.ALTERED)
        				.addAlteration(new Alteration(AlterationType.NEWDEST, "Woking")).build())
        			.add(new ContingencyAlteration.Builder()
        				.setServiceGroup(this.serviceGroupDao.findByUid("2L-B"))
        				.setDirection(Direction.UP)
        				.setAffect(Affect.ALTERED)
        				.addAlteration(new Alteration(AlterationType.NEWORIGIN, "Woking")).build())
        			.add(new ContingencyAlteration.Builder()
        				.setServiceGroup(this.serviceGroupDao.findByUid("1A"))
        				.setDirection(Direction.DOWN)
        				.setAffect(Affect.ALTERED)
        				.addAlteration(new Alteration(AlterationType.NEWDEST, "Woking")).build())
        			.add(new ContingencyAlteration.Builder()
        				.setServiceGroup(this.serviceGroupDao.findByUid("2A"))
        				.setDirection(Direction.DOWN)
        				.setAffect(Affect.ALTERED)
        				.addAlteration(new Alteration(AlterationType.NEWDEST, "Woking")).build())
        			.add(new ContingencyAlteration.Builder()
        				.setServiceGroup(this.serviceGroupDao.findByUid("1A"))
        				.setDirection(Direction.UP)
        				.setAffect(Affect.ALTERED)
        				.addAlteration(new Alteration(AlterationType.NEWORIGIN, "Woking")).build())
        			.add(new ContingencyAlteration.Builder()
        				.setServiceGroup(this.serviceGroupDao.findByUid("2A"))
        				.setDirection(Direction.UP)
        				.setAffect(Affect.ALTERED)
        				.addAlteration(new Alteration(AlterationType.NEWORIGIN, "Woking")).build())
        			.add(new ContingencyAlteration.Builder()
        				.setServiceGroup(this.serviceGroupDao.findByUid("1L"))
        				.setDirection(Direction.DOWN).setAffect(Affect.ALTERED)
        				.addAlteration(new Alteration(AlterationType.NEWDEST, "Basingstoke")).build())
        			.add(new ContingencyAlteration.Builder()
        				.setServiceGroup(this.serviceGroupDao.findByUid("1L"))
        				.setDirection(Direction.UP)
        				.setAffect(Affect.ALTERED)
        				.addAlteration(new Alteration(AlterationType.NEWORIGIN, "Basingstoke")).build())
        			.add(new ContingencyAlteration.Builder()
        				.setServiceGroup(this.serviceGroupDao.findByUid("1D"))
        				.setDirection(Direction.BOTH).setAffect(Affect.CANCELLED).build())
        			.add(new ContingencyAlteration.Builder()
        				.setServiceGroup(this.serviceGroupDao.findByUid("2M"))
        				.setDirection(Direction.BOTH)
        				.setAffect(Affect.CANCELLED).build())
        			.add(new ContingencyAlteration.Builder()
        				.setServiceGroup(this.serviceGroupDao.findByUid("2J"))
        				.setDirection(Direction.BOTH)
        				.setAffect(Affect.CANCELLED).build())
        			.add(new ContingencyAlteration.Builder()
        				.setServiceGroup(this.serviceGroupDao.findByUid("2H"))
        				.setDirection(Direction.BOTH)
        				.setAffect(Affect.CANCELLED).build()).build();
        this.entityManager.persist((Object)contingencyPlan);
        
        contingencyPlan = new ContingencyPlan.PlanBuilder().setCode("SRP2").setTitle("8tph reduction Raynes Park-Waterloo").add(new ContingencyAlteration.Builder().setServiceGroup(this.serviceGroupDao.findByUid("1L")).setDirection(Direction.DOWN).setAffect(Affect.ALTERED).addAlteration(new Alteration(AlterationType.NEWDEST, "Basingstoke")).build()).add(new ContingencyAlteration.Builder().setServiceGroup(this.serviceGroupDao.findByUid("1L")).setDirection(Direction.UP).setAffect(Affect.ALTERED).addAlteration(new Alteration(AlterationType.NEWORIGIN, "Basingstoke")).build()).add(new ContingencyAlteration.Builder().setServiceGroup(this.serviceGroupDao.findByUid("1D")).setDirection(Direction.BOTH).setAffect(Affect.CANCELLED).build()).add(new ContingencyAlteration.Builder().setServiceGroup(this.serviceGroupDao.findByUid("2M")).setDirection(Direction.BOTH).setAffect(Affect.CANCELLED).build()).add(new ContingencyAlteration.Builder().setServiceGroup(this.serviceGroupDao.findByUid("2J")).setDirection(Direction.BOTH).setAffect(Affect.CANCELLED).build()).build();
        this.entityManager.persist((Object)contingencyPlan);
        
        contingencyPlan = new ContingencyPlan.PlanBuilder().setCode("SRP3").setTitle("Windsor Lines AM Peak").add(new ContingencyAlteration.Builder().setServiceGroup(this.serviceGroupDao.findByUid("2R-W")).setDirection(Direction.BOTH).setAffect(Affect.CANCELLED).build()).add(new ContingencyAlteration.Builder().setServiceGroup(this.serviceGroupDao.findByUid("2V")).setDirection(Direction.BOTH).setAffect(Affect.CANCELLED).build()).build();
        this.entityManager.persist((Object)contingencyPlan);
        
        contingencyPlan = new ContingencyPlan.PlanBuilder().setCode("SRP4").setTitle("Windsor Lines PM Peak").add(new ContingencyAlteration.Builder().setServiceGroup(this.serviceGroupDao.findByUid("2R-W")).setDirection(Direction.BOTH).setAffect(Affect.CANCELLED).build()).add(new ContingencyAlteration.Builder().setServiceGroup(this.serviceGroupDao.findByUid("2V")).setDirection(Direction.BOTH).setAffect(Affect.CANCELLED).build()).build();
        this.entityManager.persist((Object)contingencyPlan);
        
        contingencyPlan = new ContingencyPlan.PlanBuilder().setCode("SRP5").setTitle("Windsor Both Lines Barnes-Waterloo").add(new ContingencyAlteration.Builder().setServiceGroup(this.serviceGroupDao.findByUid("2S")).setDirection(Direction.DOWN).setAffect(Affect.ALTERED).addAlteration(new Alteration(AlterationType.NEWDEST, "Kew Bridge")).build()).add(new ContingencyAlteration.Builder().setServiceGroup(this.serviceGroupDao.findByUid("2U")).setDirection(Direction.DOWN).setAffect(Affect.ALTERED).addAlteration(new Alteration(AlterationType.RUNVIA, "Kingston")).build()).add(new ContingencyAlteration.Builder().setServiceGroup(this.serviceGroupDao.findByUid("2C")).setDirection(Direction.DOWN).setAffect(Affect.ALTERED).addAlteration(new Alteration(AlterationType.RUNVIA, "Chertsey")).build()).add(new ContingencyAlteration.Builder().setServiceGroup(this.serviceGroupDao.findByUid("2K")).setDirection(Direction.DOWN).setAffect(Affect.ALTERED).addAlteration(new Alteration(AlterationType.NEWDEST, "Richmond")).build()).add(new ContingencyAlteration.Builder().setServiceGroup(this.serviceGroupDao.findByUid("2R-W")).setDirection(Direction.BOTH).setAffect(Affect.CANCELLED).build()).add(new ContingencyAlteration.Builder().setServiceGroup(this.serviceGroupDao.findByUid("2V")).setDirection(Direction.BOTH).setAffect(Affect.CANCELLED).build()).add(new ContingencyAlteration.Builder().setServiceGroup(this.serviceGroupDao.findByUid("2H")).setDirection(Direction.BOTH).setAffect(Affect.CANCELLED).build()).add(new ContingencyAlteration.Builder().setServiceGroup(this.serviceGroupDao.findByUid("1C")).setDirection(Direction.BOTH).setAffect(Affect.CANCELLED).build()).add(new ContingencyAlteration.Builder().setServiceGroup(this.serviceGroupDao.findByUid("1N")).setDirection(Direction.BOTH).setAffect(Affect.CANCELLED).build()).build();
        this.entityManager.persist((Object)contingencyPlan);
    }
}
