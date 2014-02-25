package com.aps.wicc.ejb.security;

import org.picketlink.*;
import org.apache.deltaspike.security.api.authorization.*;

import java.util.*;

import javax.inject.Inject;

public class CustomAccessDecsionVoter implements AccessDecisionVoter
{
    private static final long serialVersionUID = 1L;
    
    @Inject
    private Identity identity;
    
    public Set<SecurityViolation> checkPermission(final AccessDecisionVoterContext accessDecisionVoterContext) {
        final Set<SecurityViolation> set = new HashSet<SecurityViolation>();
        if (!this.identity.isLoggedIn()) {
            set.add((SecurityViolation)new SecurityViolation() {
                private static final long serialVersionUID = 1L;
                
                public String getReason() {
                    return "User is not logged in";
                }
            });
        }
        return set;
    }
}
