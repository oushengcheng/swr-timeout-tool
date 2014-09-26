package com.aps.wicc.web.email;

import java.io.IOException;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.enterprise.inject.Produces;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.TemplateExceptionHandler;
import freemarker.template.Version;

@Startup
@Singleton
public class FreemarkerConfig {

    private Configuration cfg;

    @PostConstruct
    void atStartUp() throws IOException {

        cfg = new Configuration();

        cfg.setClassForTemplateLoading(ContentBuilder.class, "");
        cfg.setObjectWrapper(new DefaultObjectWrapper());
        cfg.setDefaultEncoding("UTF-8");
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
        cfg.setIncompatibleImprovements(new Version(2, 3, 20));

    }

    @Produces
    public Configuration produceConfiguration() {
        return cfg;
    }

}
