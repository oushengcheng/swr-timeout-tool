package com.aps.wicc.web.email;

import java.io.IOException;
import java.io.Serializable;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.apache.deltaspike.core.api.config.view.metadata.ViewConfigResolver;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.format.DateTimeFormat;

import com.aps.wicc.ejb.IncidentBean;
import com.aps.wicc.ejb.parameters.PlanViewIdParameter;
import com.aps.wicc.ejb.parameters.ScrollSpeed;
import com.aps.wicc.model.Incident;
import com.aps.wicc.model.ServiceGroupAlteration;
import com.aps.wicc.web.Messages;
import com.aps.wicc.web.Pages;
import com.aps.wicc.web.formatter.LongFormatter;
import com.google.common.collect.Lists;

import freemarker.template.Configuration;
import freemarker.template.TemplateException;

class ContentBuilder implements Serializable {

    private static final long serialVersionUID = 1L;

    private Configuration cfg;
    private IncidentBean incidentBean;
    private DateTimeZone dateTimeZone;
    private Messages messages;
    private String requiredid;
    private String defaultscrollspeed;

    private Map<Object, Object> root = new HashMap<>();

    private Incident incident;

    private String subject;
    private StringWriter out = new StringWriter();

    @Inject
    private ViewConfigResolver viewConfigResolver;

    @Inject
    public ContentBuilder(Configuration cfg,
                          IncidentBean incidentBean,
                          DateTimeZone dateTimeZone,
                          Messages messages,
                          @PlanViewIdParameter String requiredid,
                          @ScrollSpeed String defaultscrollspeed) {
        this.cfg = cfg;
        this.incidentBean = incidentBean;
        this.dateTimeZone = dateTimeZone;
        this.messages = messages;
        this.requiredid = requiredid;
        this.defaultscrollspeed = defaultscrollspeed;

    }

    public Content createContent() {
        try {
            return buildContent();
        } catch (IOException | TemplateException e) {
            throw new ContentBuildFailedException(e);
        }
    }

    private Content buildContent() throws IOException, TemplateException {
        incident = incidentBean.getOpenIncident();
        addSubject();
        addIncident();
        addAlterations();
        addTimes();
        addResources();
        addUrl();
        writeOut();
        return new Content(subject, out.toString());
    }

    private void addSubject() {
        subject = messages.publishEmailSubject(printTime("HH:mm EEE d MMM yyyy", new DateTime()));
    }

    private void addIncident() {
        root.put("incident", incident);
    }

    private void addAlterations() {
        List<Map<String, String>> alterations = new ArrayList<>();
        for (ServiceGroupAlteration alteration : incident.getServiceGroupAlterations()) {
            Map<String, String> map = new HashMap<>();
            map.put("longformat", new LongFormatter(alteration).format());
            map.put("freeform", alteration.getFreeform());
            alterations.add(map);
        }
        root.put("alterations", alterations);
    }

    public void addTimes() {
        root.put("lastpublished", printTime("HH:mm", incident.getLastPublished()));
        root.put("nextreview", printTime("HH:mm", incident.getNextReview()));
        root.put("created", printTime("HH:mm:ss", new DateTime(dateTimeZone)));
    }

    private String printTime(String format, DateTime dateTime) {
        return DateTimeFormat.forPattern(format).withZone(dateTimeZone).print(dateTime);
    }

    private void addResources() {
        root.put("msg", ResourceBundleMapper.create("resources"));
    }

    private void addUrl() {
    	root.put("staticurl", contextUrl() + createRelativeUrl());
	}

	private String createRelativeUrl() {
		String viewId = createViewId();
    	Map<String, List<String>> map = createParameterMap();
		return FacesContext.getCurrentInstance().getApplication().getViewHandler().getBookmarkableURL(FacesContext.getCurrentInstance(), viewId, map, false);
	}

	private String createViewId() {
		return viewConfigResolver.getConfigDescriptor(Pages.Planviewscroll.class).getPath();
	}

	private Map<String, List<String>> createParameterMap() {
		Map<String, List<String>> map = new HashMap<>();
    	map.put(Pages.PARAMETER_ID, Lists.newArrayList(requiredid));
    	map.put(Pages.PARAMETER_SCROLL_SPEED, Lists.newArrayList(defaultscrollspeed));
		return map;
	}

    private String contextUrl() {
    	HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
    	return request.getRequestURL().toString().replace(request.getRequestURI().substring(0), "") ;
    }
    private void writeOut() throws IOException, TemplateException {
        cfg.getTemplate("gmail.ftl").process(root, out);
    }
}
