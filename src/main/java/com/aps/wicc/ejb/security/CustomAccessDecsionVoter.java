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
        
    	final Set<SecurityViolation> set = new HashSet<SecurityViolation>();
            	
    	Access access = accessDecisionVoterContext.getMetaDataFor(Access.class.getName(), Access.class);
    	
    	boolean match = false;
    	
    	for (String role : access.role()) {
    	
    		if (authorizationChecker.hasApplicationRole(role)) {
    			
    			match = true;
    			
    		}
    			
    	}
    	
    	if (!match) {
    		    		
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
