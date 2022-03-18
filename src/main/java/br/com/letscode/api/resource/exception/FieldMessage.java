package br.com.letscode.api.resource.exception;

import java.io.Serializable;

public class FieldMessage implements Serializable {

    private String fildName;
    private String message;
    private String messageDeveloper;

    public FieldMessage() {
    }

    public FieldMessage(String fildName, String message) {
        this.fildName = fildName;
        this.message = message;
    }

    public FieldMessage(String fildName, String message, String messageDeveloper) {
        this.fildName = fildName;
        this.message = message;
        this.messageDeveloper = messageDeveloper;
    }

    public String getFildName() {
        return fildName;
    }

    public void setFildName(String fildName) {
        this.fildName = fildName;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessageDeveloper() {
        return messageDeveloper;
    }

    public void setMessageDeveloper(String messageDeveloper) {
        this.messageDeveloper = messageDeveloper;
    }
}
