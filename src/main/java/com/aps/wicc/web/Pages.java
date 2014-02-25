package com.aps.wicc.web;

import org.apache.deltaspike.core.api.config.view.*;
import org.apache.deltaspike.core.api.config.view.controller.*;
import org.apache.deltaspike.jsf.api.config.view.*;
import com.aps.wicc.web.backing.*;
import org.apache.deltaspike.core.api.config.view.navigation.*;
import org.apache.deltaspike.security.api.authorization.*;
import com.aps.wicc.ejb.security.*;

@Folder(name = "/")
public interface Pages
{
    @ViewControllerRef(LoginBacking.class)
    public static class Login implements ViewConfig
    {
    }
    
    @View(navigation = View.NavigationMode.REDIRECT)
    public static class Editsummary implements ViewConfig, SecuredPages
    {
    }
    
    @View(navigation = View.NavigationMode.REDIRECT)
    public static class Editdetail implements ViewConfig, SecuredPages
    {
    }
    
    @View(navigation = View.NavigationMode.REDIRECT)
    public static class Contingency implements ViewConfig, SecuredPages
    {
    }
    
    @View(navigation = View.NavigationMode.REDIRECT)
    public static class History implements ViewConfig, SecuredPages
    {
    }
    
    @View(navigation = View.NavigationMode.REDIRECT)
    public static class Sort implements ViewConfig, SecuredPages
    {
    }
    
    @ViewControllerRef(IncidentViewBacking.class)
    @View(navigation = View.NavigationMode.REDIRECT, viewParams = View.ViewParameterMode.INCLUDE)
    @NavigationParameter(key = "id", value = "#{planViewIdParameter}")
    public static class Planview implements ViewConfig
    {
    }
    
    @ViewControllerRef(IncidentViewBacking.class)
    @View(navigation = View.NavigationMode.REDIRECT, viewParams = View.ViewParameterMode.INCLUDE)
    @NavigationParameter(key = "id", value = "#{planViewIdParameter}")
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
