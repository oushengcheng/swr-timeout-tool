package com.aps.wicc.model;

public enum Status
{
    OPEN("enum.status.open"),
    DRAFT("enum.status.draft"),
    CLOSED("enum.status.closed");

    private String message;

    private Status(final String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }
}
