package com.aps.wicc.web;

import org.apache.deltaspike.core.api.message.MessageBundle;
import org.apache.deltaspike.core.api.message.MessageContextConfig;
import org.apache.deltaspike.core.api.message.MessageTemplate;

@MessageBundle
@MessageContextConfig(messageSource = { "messages" })
public interface Messages {

    @MessageTemplate("{login_incorrect_credentials}")
    String incorrectCredentials();

    @MessageTemplate("{time_not_valid}")
    String timeNotValid();

    @MessageTemplate("{stale_data}")
    String staleData();

    @MessageTemplate("{default_footer}")
    String defaultFooter();

    @MessageTemplate("{publish_email_subject}")
    String publishEmailSubject(String description);

    @MessageTemplate("{sleep_incident_description}")
	String sleepIncidentDescription();

    @MessageTemplate("{action_incident_description}")
	String actionIncidentDescription();

    @MessageTemplate("{sleep_incident_title}")
	String sleepIncidentTitle(String time);

    @MessageTemplate("{action_incident_title}")
	String actionIncidentTitle(String time);

}
