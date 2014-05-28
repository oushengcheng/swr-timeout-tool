package com.aps.wicc.ejb.security;

import java.util.HashSet;
import java.util.Set;

import javax.inject.Inject;

import org.apache.deltaspike.security.api.authorization.AccessDecisionVoter;
import org.apache.deltaspike.security.api.authorization.AccessDecisionVoterContext;
import org.apache.deltaspike.security.api.authorization.SecurityViolation;

public class CustomAccessDecsionVoter implements AccessDecisionVoter {
	
    private static final long serialVersionUID = 1L;
    
    private AuthorizationChecker authorizationChecker;
    
    @Inject
    public CustomAccessDecsionVoter(AuthorizationChecker authorizationChecker) {
    	this.authorizationChecker = authorizationChecker;
    }
    
    public Set<SecurityViolation> checkPermission(final AccessDecisionVoterContext accessDecisionVoterContext) {
        Access access = getAccessMetaData(accessDecisionVoterContext);    	
    	boolean accessAllowed = testIfUserHasAccess(access);
    	return createSecurityViolationsIfRequired(accessAllowed);
        
    }
    
    private Access getAccessMetaData(AccessDecisionVoterContext accessDecisionVoterContext) {
    
    	return accessDecisionVoterContext.getMetaDataFor(Access.class.getName(), Access.class);
    	
    }
    
    private boolean testIfUserHasAccess(Access access) {
    	
    	if (access == null) {
    		
    		return true;
    		
    	}
    	
    	boolean accessAllowed = false;
    	
    	for (String role : access.role()) {
        	
    		if (authorizationChecker.hasApplicationRole(role)) {
    			    			
    			accessAllowed = true;
    			
    		}
    			
    	}
    	
    	return accessAllowed;
    }
    
    private Set<SecurityViolation> createSecurityViolationsIfRequired(boolean accessAllowed) {
    	
    	Set<SecurityViolation> set = new HashSet<SecurityViolation>();
    	
    	if (!accessAllowed) {
    		    	
    		set.add(new SecurityViolation() {
	             
    			private static final long serialVersionUID = 1L;
	             
	            public String getReason() {
	                return "User is not logged in";
	            }
	             
	        });
    	 
    	}
    	
    	return set;
    	 
    }
}
