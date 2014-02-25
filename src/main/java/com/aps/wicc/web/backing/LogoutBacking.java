package com.aps.wicc.web.backing;

import javax.inject.*;
import javax.enterprise.context.*;

import org.picketlink.*;

import javax.faces.context.*;

import com.aps.wicc.web.*;

@Named
@RequestScoped
public class LogoutBacking
{
    private Identity identity;
    private FacesContext facesContext;
    
    public LogoutBacking() {
        super();
    }
    
    @Inject
    public LogoutBacking(final Identity identity, final FacesContext facesContext) {
        super();
        this.identity = identity;
        this.facesContext = facesContext;
    }
    
    public Class<?> logout() {
        this.facesContext.getExternalContext().invalidateSession();
        this.identity.logout();
        return Pages.Login.class;
    }
}
