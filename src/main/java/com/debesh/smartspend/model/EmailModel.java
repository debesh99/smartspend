package com.debesh.smartspend.model;

public class EmailModel {
    private String recipient;
    private String msgBody;
    private String subject;

    public String getMsgBody() {
        return msgBody;
    }

    public void setMsgBody(String msgBody) {
        this.msgBody = msgBody;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    @Override
    public String toString() {
        return "EmailModel{" +
                "msgBody='" + msgBody + '\'' +
                ", recipient='" + recipient + '\'' +
                ", subject='" + subject + '\'' +
                '}';
    }
}
