package com.aps.wicc.web.email;

class Content {

    private String subject;
    private String body;

    public Content(String subject, String body) {
        this.subject = subject;
        this.body = body;
    }

    public String getSubject() {
        return subject;
    }

    public String getBody() {
        return body;
    }


}
