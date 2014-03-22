package com.aps.wicc.web.backing;

import javax.annotation.PostConstruct;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.joda.time.DateTimeConstants;

@RequestScoped
@Named
public class PollBacking {

	@Inject FacesContext facesContext;
	@Inject Conversation conversation;
	
	private final static int THIRTY_MINUTES = 30;
	
	@PostConstruct
	public void keepSessionAndConversationAlive() {
		
		facesContext.getExternalContext().getSession(false); // Keep session alive
		
		if (!conversation.isTransient()) {
			
			conversation.setTimeout(DateTimeConstants.MILLIS_PER_MINUTE * THIRTY_MINUTES); // Keep conversation active for another 30 minutes
					
		}			
	}
}
