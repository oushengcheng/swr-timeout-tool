package com.aps.wicc.web.email;

import java.io.Serializable;

import javax.enterprise.inject.Instance;
import javax.inject.Inject;

import com.aps.wicc.ejb.email.Emailer;

public class EmailSend implements Serializable {

    private static final long serialVersionUID = 1L;

    private Emailer emailer;
    private Instance<ContentBuilder> contentBuilder;
    private String publishDistributionList;

    @Inject
    public EmailSend(Emailer emailer,
                     Instance<ContentBuilder> contentBuilder,
                     @PublishDistributionList String publishDistributionList) {
        this.emailer = emailer;
        this.contentBuilder = contentBuilder;
        this.publishDistributionList = publishDistributionList;
    }

    public void send() {
        String to = publishDistributionList;
        Content content = contentBuilder.get().createContent();
        emailer.send(to, content.getSubject(), content.getBody());
    }

}
