package com.aps.wicc.web;

import org.apache.deltaspike.core.api.config.view.*;
import org.apache.deltaspike.core.api.config.view.controller.*;
import org.apache.deltaspike.jsf.api.config.view.*;

import com.aps.wicc.web.backing.*;

import org.apache.deltaspike.core.api.config.view.navigation.*;
import org.apache.deltaspike.security.api.authorization.*;

import com.aps.wicc.ejb.security.*;
import com.aps.wicc.model.Roles;

@Folder(name = "/")
public interface Pages
{
    @ViewControllerRef(LoginBacking.class)
    public static class Login implements ViewConfig
    {
    }
    
    @View(navigation = View.NavigationMode.REDIRECT)
    @Access(role=Roles.EDIT_ROLE)
    public class Editsummary implements ViewConfig, SecuredPages
    {
    }
    
    @View(navigation = View.NavigationMode.REDIRECT)
    @Access(role=Roles.EDIT_ROLE)
    public class Editdetail implements ViewConfig, SecuredPages
    {
    }
    
    @View(navigation = View.NavigationMode.REDIRECT)
    @Access(role=Roles.EDIT_ROLE)
    public class Contingency implements ViewConfig, SecuredPages
    {
    }
    
    @View(navigation = View.NavigationMode.REDIRECT)
    @Access(role=Roles.ADMIN_ROLE)
    public static class History implements ViewConfig, SecuredPages
    {
    }
    
    @View(navigation = View.NavigationMode.REDIRECT)
    @Access(role=Roles.EDIT_ROLE)
    public static class Sort implements ViewConfig, SecuredPages
    {
    }
        
    @View(navigation = View.NavigationMode.REDIRECT)
    @Access(role={Roles.EDIT_ROLE, Roles.VIEW_ROLE})
    public static class Planview implements ViewConfig, SecuredPages
    {
    }
    
    @ViewControllerRef(ScrollViewBacking.class)
    @View(navigation = View.NavigationMode.REDIRECT, viewParams = View.ViewParameterMode.INCLUDE)
    @NavigationParameter.List({
    		@NavigationParameter(key = "id", value = "#{planViewIdParameter}"),
    		@NavigationParameter(key = "scrollspeed", value = "#{scrollSpeed}")    		
    })
    public static class Planviewscroll implements ViewConfig
    {
    }
    
    @View(navigation = View.NavigationMode.REDIRECT)
    public static class Servererror implements ViewConfig
    {
    }
    
    public static class Timedout implements ViewConfig
    {
    }
    
    @View(navigation = View.NavigationMode.REDIRECT)
    public static class Accessdenied implements ViewConfig
    {
    }
    
    @View(navigation = View.NavigationMode.REDIRECT)
    public static class Pagenotfound implements ViewConfig
    {
    }
    
    @Secured(value = { CustomAccessDecsionVoter.class }, errorView = Accessdenied.class)    
    public interface SecuredPages
    {
    }
}
