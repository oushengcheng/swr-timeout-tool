package com.aps.wicc.web.email;

import java.io.IOException;
import java.io.Serializable;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.format.DateTimeFormat;

import com.aps.wicc.ejb.IncidentBean;
import com.aps.wicc.model.Incident;
import com.aps.wicc.model.ServiceGroupAlteration;
import com.aps.wicc.web.Messages;
import com.aps.wicc.web.formatter.LongFormatter;

import freemarker.template.Configuration;
import freemarker.template.TemplateException;

class ContentBuilder implements Serializable {

    private static final long serialVersionUID = 1L;
    private Configuration cfg;
    private IncidentBean incidentBean;
    private DateTimeZone dateTimeZone;
    private Messages messages;
    private Map<Object, Object> root = new HashMap<>();

    private Incident incident;

    private String subject;
    private StringWriter out = new StringWriter();

    @Inject
    public ContentBuilder(Configuration cfg,
                          IncidentBean incidentBean,
                          DateTimeZone dateTimeZone,
                          Messages messages) {
        this.cfg = cfg;
        this.incidentBean = incidentBean;
        this.dateTimeZone = dateTimeZone;
        this.messages = messages;
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
        writeOut();
        return new Content(subject, out.toString());
    }

    private void addSubject() {
        subject = messages.publishEmailSubject(incident.getTitle());
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
        return DateTimeFormat.forPattern(format).print(dateTime);
    }

    private void addResources() {
        root.put("msg", ResourceBundleMapper.create("resources"));
    }

    private void writeOut() throws IOException, TemplateException {
        cfg.getTemplate("gmail.ftl").process(root, out);
    }
}
