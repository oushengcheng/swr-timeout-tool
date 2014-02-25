package com.aps.wicc.web.backing;

import javax.inject.*;
import javax.validation.constraints.NotNull;
import javax.enterprise.context.*;

import org.picketlink.*;
import org.picketlink.credential.*;

import com.aps.wicc.ejb.security.*;

import org.apache.deltaspike.core.api.config.view.navigation.*;
import org.apache.deltaspike.jsf.api.message.*;
import org.picketlink.authentication.*;
import org.apache.deltaspike.core.api.config.view.controller.*;
import org.apache.deltaspike.core.api.config.view.*;

import com.aps.wicc.web.*;

@Named
@RequestScoped
public class LoginBacking
{
    private Identity identity;
    private DefaultLoginCredentials logInCredentials;
    private AuthorizationChecker authorizationChecker;
    private ViewNavigationHandler viewNavigationHandler;
    private JsfMessage<Messages> messages;
    
    @NotNull(message="{login_no_username}")    		    	
    private String username;
    		
    @NotNull(message="{login_no_pasword}")
    private String password;
      
    
    
    public LoginBacking() {
        super();
    }
    
    @Inject
    public LoginBacking(final Identity identity, final DefaultLoginCredentials logInCredentials, final AuthorizationChecker authorizationChecker, final ViewNavigationHandler viewNavigationHandler, JsfMessage<Messages> messages) {
        super();
        this.identity = identity;
        this.logInCredentials = logInCredentials;
        this.authorizationChecker = authorizationChecker;
        this.viewNavigationHandler = viewNavigationHandler;
        this.messages = messages;
    }
    
    public String getUsername() {
        return this.username;
    }
    
    public void setUsername(final String username) {
        this.username = username;
    }
    
    public String getPassword() {
        return this.password;
    }
    
    public void setPassword(final String password) {
        this.password = password;
    }
    
    public Class<?> login() {
        this.logInCredentials.setUserId(this.username);
        this.logInCredentials.setPassword(this.password);
        try {
            if (Identity.AuthenticationResult.FAILED.equals((Object)this.identity.login())) {
                this.messages.addError().incorrectCredentials();
                return null;
            }
            return this.navigate();
        }
        catch (UserAlreadyLoggedInException e) {
            return this.navigate();
        }
    }
    
    @PreRenderView
    protected void preRenderView() {
        if (this.identity.isLoggedIn()) {
            this.viewNavigationHandler.navigateTo(this.navigate());
        }
    }
    
    private Class<? extends ViewConfig> navigate() {
        if (this.authorizationChecker.hasApplicationRole("admin")) {
            return (Class<? extends ViewConfig>)Pages.History.class;
        }
        if (this.authorizationChecker.hasApplicationRole("edit")) {
            return (Class<? extends ViewConfig>)Pages.Editsummary.class;
        }
        throw new RuntimeException("Unknown role on log in");
    }
}
