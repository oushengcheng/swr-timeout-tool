package com.aps.wicc.web;

import org.slf4j.*;
import javax.faces.event.*;

public class LifeCycleListener implements PhaseListener
{
    private static final long serialVersionUID = 1L;
    private static final Logger logger;
    
    static {
        logger = LoggerFactory.getLogger(LifeCycleListener.class);
    }
    
    public PhaseId getPhaseId() {
        return PhaseId.ANY_PHASE;
    }
    
    public void beforePhase(final PhaseEvent event) {
        LifeCycleListener.logger.debug("START PHASE " + event.getPhaseId());        
    }
    
    public void afterPhase(final PhaseEvent event) {
        LifeCycleListener.logger.debug("END PHASE " + event.getPhaseId());        
    }
}
