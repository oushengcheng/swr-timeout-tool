package com.aps.wicc.web.backing.view;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.deltaspike.core.api.config.view.ViewConfig;
import org.apache.deltaspike.core.api.config.view.metadata.ViewConfigResolver;

import com.aps.wicc.web.Pages;


@RequestScoped
@Named
public class SwitcherBacking implements Serializable {

    private static final long serialVersionUID = 1L;

    private ViewConfigResolver viewConfigResolver;
    private FacesContext facesContext;

    public SwitcherBacking() {
    }

    @Inject
    public SwitcherBacking(ViewConfigResolver viewConfigResolver, FacesContext facesContext) {
        this.viewConfigResolver = viewConfigResolver;
        this.facesContext = facesContext;
    }

    public Class<? extends ViewConfig> scrollingView() {
        return Pages.Scrollingview.class;
    }

    public Class<? extends ViewConfig> staticView() {
        return Pages.Staticview.class;
    }

    public boolean isStaticView() {
        return testPage(Pages.Staticview.class);
    }


    private boolean testPage(Class<? extends ViewConfig> page) {
        return viewConfigResolver.getViewConfigDescriptor(facesContext.getViewRoot().getViewId()).getConfigClass().equals(page);
    }


}
