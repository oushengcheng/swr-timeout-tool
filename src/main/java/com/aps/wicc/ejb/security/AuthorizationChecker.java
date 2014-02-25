package com.aps.wicc.ejb.security;

import javax.inject.*;

import org.picketlink.*;
import org.picketlink.idm.*;
import org.picketlink.idm.model.*;
import org.picketlink.idm.model.basic.*;

@Named
public class AuthorizationChecker
{
	@Inject
    private Identity identity;
	
	@Inject
    private IdentityManager identityManager;
	
	@Inject
    private RelationshipManager relationshipManager;
    
    public boolean hasApplicationRole(final String roleName) {
        final Role role = BasicModel.getRole(this.identityManager, roleName);
        return BasicModel.hasRole(this.relationshipManager, (IdentityType)this.identity.getAccount(), role);
    }
    
    public boolean isMember(final String groupName) {
        final Group group = BasicModel.getGroup(this.identityManager, groupName);
        return BasicModel.isMember(this.relationshipManager, this.identity.getAccount(), group);
    }
    
    public boolean hasGroupRole(final String roleName, final String groupName) {
        final Group group = BasicModel.getGroup(this.identityManager, groupName);
        final Role role = BasicModel.getRole(this.identityManager, roleName);
        return BasicModel.hasGroupRole(this.relationshipManager, (IdentityType)this.identity.getAccount(), role, group);
    }
}
