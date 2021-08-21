package com.gerard.site.entity;

import java.sql.Date;

/**
 * Class represents single record from table <i>gerard.request</i> ,
 * note that primary key of record is represented
 * by is superclass AbstractEntity {@link AbstractEntity}
 * by it's instance filed 'id' {@link AbstractEntity#id} .
 *
 * <b>Primary key column is named 'request_id' </b> .
 *
 * @author Liliya Siadzelnikava
 * @version 1.0
 */
public class RequestEntity extends AbstractEntity<Integer> {
    /**
     * Possible values for instance field {@code requestStatus}
     * {@link RequestEntity#requestStatus} .
     */
    public enum RequestStatus {
        /**
         * Represents next request's status:
         * request have not been proceed yet.
         */
        PENDING,


        /**
         * Represents next request's status:
         * request was accepted.
         */
        CONTACT,

        /**
         * Represents next request's status:
         * request was rejected.
         */
        REJECTED
    }

    /**
     * Possible values for instance field {@code requestType}
     * {@link RequestEntity#requestType} .
     */
    public enum RequestType {
        /**
         * Represents next request's status:
         * request for puppy.
         */
        PUPPY,

        /**
         * Represents next request's status:
         * request for wedding.
         */
        WEDDING,
    }

    /**
     * Represents column 'request_status' in the table <i>gerard.request</i> .
     */
    private RequestStatus requestStatus;

    /**
     * Represents column 'request_type' in the table <i>gerard.request</i> .
     */
    private RequestType requestType;

    /**
     * Represents column 'app_email' in the table <i>gerard.request</i> .
     */
    private String email;

    /**
     * Represents column 'dog_id' in the table <i>gerard.request</i> .
     */
    private int dogId;

    /**
     * Represents column 'content' in the table <i>gerard.request</i> .
     */
    private String content;

    /**
     * Represents column 'date_fact' in the table <i>gerard.request</i> .
     */
    private Date dateFact;

    /**
     * Represents column 'reply' in the table <i>gerard.request</i> .
     * May store null value in database .
     */
    private String reply;


    public RequestEntity() {
    }

    public RequestStatus getRequestStatus() {
        return requestStatus;
    }

    public void setRequestStatus(RequestStatus requestStatus) {
        this.requestStatus = requestStatus;
    }

    public RequestType getRequestType() {
        return requestType;
    }

    public void setRequestType(RequestType requestType) {
        this.requestType = requestType;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getDogId() {
        return dogId;
    }

    public void setDogId(int dogId) {
        this.dogId = dogId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getDateFact() {
        return dateFact;
    }

    public void setDateFact(Date dateFact) {
        this.dateFact = dateFact;
    }

    public String getReply() {
        return reply;
    }

    public void setReply(String reply) {
        this.reply = reply;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object instanceof RequestEntity requestEntity) {
            return id == null
                    ? requestEntity.id == null
                    : id.equals(requestEntity.id);
        }
        return false;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        int hashcode = super.hashCode();
        hashcode = hash * hashcode + (requestStatus == null ? 0 : requestStatus.hashCode());
        hashcode = hash * hashcode + (requestType == null ? 0 : requestType.hashCode());
        hashcode = hash * hashcode + (email == null ? 0 : email.hashCode());
        hashcode = hash * hashcode + dogId;
        hashcode = hash * hashcode + (content == null ? 0 : content.hashCode());
        hashcode = hash * hashcode + (dateFact == null ? 0 : dateFact.hashCode());
        hashcode = hash * hashcode + (reply == null ? 0 : reply.hashCode());
        return hashcode;
    }

    @Override
    public String toString() {
        return "RequestEntity{"
                + "id=" + id
                + ", requestStatus=" + requestStatus
                + ", requestType=" + requestType
                + ", email=" + email
                + ", dogId=" + dogId
                + ", content='" + content + '\''
                + ", dateFact=" + dateFact
                + ", reply='" + reply + '\''
                + '}';
    }
}
