package com.aps.wicc.ejb.initialisation;

import javax.inject.Inject;

import org.apache.deltaspike.core.api.exclude.Exclude;
import org.apache.deltaspike.core.api.projectstage.ProjectStage;
import org.picketlink.idm.credential.*;
import org.picketlink.idm.model.*;
import org.picketlink.idm.model.basic.*;
import org.picketlink.idm.*;

@Exclude(ifProjectStage = { ProjectStage.Production.class })
@Primary
public class IDMInitialisationBean implements Initialisable
{
	@Inject
    private PartitionManager partitionManager;
    
    @Override
    public void init() {
        
    	final IdentityManager identityManager = this.partitionManager.createIdentityManager();
        final RelationshipManager relationshipManager = this.partitionManager.createRelationshipManager();
        
        final Role edit = new Role("edit");
        identityManager.add((IdentityType)edit);
        
        final Role admin = new Role("admin");
        identityManager.add((IdentityType)admin);
        
        final User editUser = new User("wicc");
        editUser.setEmail("");
        editUser.setFirstName("");
        editUser.setLastName("");
        identityManager.add((IdentityType)editUser);
        identityManager.updateCredential((Account)editUser, (Object)new Password("wicc"));
        BasicModel.grantRole(relationshipManager, (IdentityType)editUser, edit);
        
        final User adminUser = new User("admin");
        adminUser.setEmail("");
        adminUser.setFirstName("");
        adminUser.setLastName("");
        identityManager.add((IdentityType)adminUser);
        identityManager.updateCredential((Account)adminUser, (Object)new Password("admin"));
        BasicModel.grantRole(relationshipManager, (IdentityType)adminUser, admin);
        
    }
}
