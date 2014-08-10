package com.aps.wicc.ejb.initialisation;

import javax.inject.Inject;

import org.picketlink.idm.IdentityManager;
import org.picketlink.idm.PartitionManager;
import org.picketlink.idm.RelationshipManager;
import org.picketlink.idm.credential.Password;
import org.picketlink.idm.model.basic.BasicModel;
import org.picketlink.idm.model.basic.Role;
import org.picketlink.idm.model.basic.User;

import com.aps.wicc.model.Roles;

@SeedingPhase(phaseNo=1)
public class IDMInitialisationBean implements Seedable {

    @Inject
    private PartitionManager partitionManager;

    @Override
    public void seed() {

        IdentityManager identityManager = this.partitionManager.createIdentityManager();
        RelationshipManager relationshipManager = this.partitionManager.createRelationshipManager();

        Role edit = new Role(Roles.EDIT_ROLE);
        identityManager.add(edit);

        Role admin = new Role(Roles.ADMIN_ROLE);
        identityManager.add(admin);

        Role view = new Role(Roles.VIEW_ROLE);
        identityManager.add(view);

        User editUser = new User("wicc");
        editUser.setEmail("");
        editUser.setFirstName("");
        editUser.setLastName("");
        identityManager.add(editUser);
        identityManager.updateCredential(editUser, new Password("wicc"));
        BasicModel.grantRole(relationshipManager, editUser, edit);

        User adminUser = new User("admin");
        adminUser.setEmail("");
        adminUser.setFirstName("");
        adminUser.setLastName("");
        identityManager.add(adminUser);
        identityManager.updateCredential(adminUser, new Password("admin"));
        BasicModel.grantRole(relationshipManager, adminUser, admin);

        User viewUser = new User("crompton");
        viewUser.setEmail("");
        viewUser.setFirstName("");
        viewUser.setLastName("");
        identityManager.add(viewUser);
        identityManager.updateCredential(viewUser, new Password("crompton"));
        BasicModel.grantRole(relationshipManager, viewUser, view);

    }
}
