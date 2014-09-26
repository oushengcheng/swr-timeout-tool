package com.aps.wicc.web.email;

public class ContentBuildFailedException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public ContentBuildFailedException() {
    }

    public ContentBuildFailedException(String message) {
        super(message);
    }

    public ContentBuildFailedException(Throwable cause) {
        super(cause);
    }

    public ContentBuildFailedException(String message, Throwable cause) {
        super(message, cause);
    }

}
