package com.sophos.backendcanvas.Util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.UUID;

public class ErrorLog {

    private UUID uuid;
    private String user;
    private String className;
    private String message;

    private static final Log LOG = LogFactory.getLog(ErrorLog.class);

    public ErrorLog(String user, String className, String message) {
        this.className = className;
        this.message = message;
        this.user = user;
        this.uuid = UUID.randomUUID();
        LOG.error("UUID: " + getUUID() + " " + "User: " + this.user + " " + "Class with error: " + getClassName() + " " + "Error message: " + getMessage());
    }

    public UUID getUUID() {
        return uuid;
    }

    public String getUser() {
        return this.user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
