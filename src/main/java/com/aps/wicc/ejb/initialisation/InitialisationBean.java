package com.aps.wicc.ejb.initialisation;

import javax.ejb.*;
import javax.enterprise.inject.*;
import javax.inject.Inject;

import org.apache.commons.lang3.builder.*;

import java.util.*;

import javax.annotation.*;

@Startup
@Singleton
public class InitialisationBean
{
	
	@Inject
	@Primary
    private Instance<Initialisable> primary;
    
	@Inject
	@Secondary
    private Instance<Initialisable> secondary;
    
	@Inject org.jboss.weld.context.http.HttpConversationContext conversationContext;
	 
    @PostConstruct
    void atStartUp() {
        
    	ToStringBuilder.setDefaultStyle(ToStringStyle.SHORT_PREFIX_STYLE);
        
    	Iterator<Initialisable> iterator = this.primary.iterator();
        
    	while (iterator.hasNext()) {
            iterator.next().init();
        }
        
    	iterator = this.secondary.iterator();
        
    	while (iterator.hasNext()) {
            iterator.next().init();
        }
    }
}
