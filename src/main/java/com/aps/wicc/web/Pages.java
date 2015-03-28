package com.aps.wicc.web;

import org.apache.deltaspike.core.api.config.view.ViewConfig;
import org.apache.deltaspike.core.api.config.view.controller.ViewControllerRef;
import org.apache.deltaspike.core.api.config.view.navigation.NavigationParameter;
import org.apache.deltaspike.jsf.api.config.view.Folder;
import org.apache.deltaspike.jsf.api.config.view.View;
import org.apache.deltaspike.security.api.authorization.Secured;

import com.aps.wicc.ejb.security.Access;
import com.aps.wicc.ejb.security.CustomAccessDecsionVoter;
import com.aps.wicc.model.Roles;
import com.aps.wicc.web.backing.LoginBacking;
import com.aps.wicc.web.backing.view.ScrollViewBacking;
import com.aps.wicc.web.backing.view.ScrollingViewBacking;

@Folder(name = "/")
public interface Pages {

    @ViewControllerRef(LoginBacking.class)
    public static class Login implements ViewConfig {
    }

    @View(navigation = View.NavigationMode.REDIRECT)
    @Access(role=Roles.EDIT_ROLE)
    public class Editsummary implements ViewConfig, SecuredPages {
    }

    @View(navigation = View.NavigationMode.REDIRECT)
    @Access(role=Roles.EDIT_ROLE)
    public class Editdetail implements ViewConfig, SecuredPages {
    }

    @View(navigation = View.NavigationMode.REDIRECT)
    @Access(role=Roles.EDIT_ROLE)
    public class Editservicegroup implements ViewConfig, SecuredPages {
    }

    @View(navigation = View.NavigationMode.REDIRECT)
    @Access(role=Roles.EDIT_ROLE)
    public class Contingency implements ViewConfig, SecuredPages {
    }

    @View(navigation = View.NavigationMode.REDIRECT)
    @Access(role=Roles.ADMIN_ROLE)
    public static class History implements ViewConfig, SecuredPages {
    }

    @View(navigation = View.NavigationMode.REDIRECT)
    @Access(role=Roles.EDIT_ROLE)
    public static class Sort implements ViewConfig, SecuredPages {
    }

    @View(navigation = View.NavigationMode.REDIRECT)
    @Access(role={Roles.EDIT_ROLE, Roles.VIEW_ROLE})
    public static class Staticview implements ViewConfig, SecuredPages {
    }

    @ViewControllerRef(ScrollingViewBacking.class)
    @View(navigation = View.NavigationMode.REDIRECT, viewParams = View.ViewParameterMode.INCLUDE)
    @Access(role={Roles.EDIT_ROLE, Roles.VIEW_ROLE})
    @NavigationParameter(key = "scrollspeed", value = "#{scrollSpeed}")
    public static class Scrollingview implements ViewConfig, SecuredPages {
    }

    @ViewControllerRef(ScrollViewBacking.class)
    @View(navigation = View.NavigationMode.REDIRECT, viewParams = View.ViewParameterMode.INCLUDE)
    @NavigationParameter.List({
            @NavigationParameter(key = "id", value = "#{planViewIdParameter}"),
            @NavigationParameter(key = "scrollspeed", value = "#{scrollSpeed}")
    })
    public static class Planviewscroll implements ViewConfig {
    }

    @View(navigation = View.NavigationMode.REDIRECT)
    public static class Servererror implements ViewConfig {
    }

    public static class Timedout implements ViewConfig {
    }

    @View(navigation = View.NavigationMode.REDIRECT)
    public static class Accessdenied implements ViewConfig {
    }

    @View(navigation = View.NavigationMode.REDIRECT)
    public static class Pagenotfound implements ViewConfig {
    }

    @Secured(value = { CustomAccessDecsionVoter.class }, errorView = Accessdenied.class)
    public interface SecuredPages {
    }

}
