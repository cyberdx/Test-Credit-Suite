package com.domain;


import com.constants.DataState;
import com.constants.DataType;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.istack.internal.NotNull;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "metaData")
@Data
public class MetaData {
    @JsonProperty("id")
    @Id
    @NotNull
    private String id;

    @Transient
    @JsonProperty("state")
    @Enumerated(EnumType.STRING)
    @NotNull
    private DataState state;

    @Transient
    @JsonProperty("timestamp")
    @Temporal(TemporalType.TIMESTAMP)
    @NotNull
    private Date timestamp;

    @JsonProperty("host")
    private String host;

    @Transient
    @JsonProperty("type")
    @Enumerated(EnumType.STRING)
    private DataType type;

    private boolean alert;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public DataState getState() {
        return state;
    }

    public void setState(DataState state) {
        this.state = state;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public DataType getType() {
        return type;
    }

    public void setType(DataType type) {
        this.type = type;
    }

    public boolean isAlert() {
        return alert;
    }

    public void setAlert(boolean alert) {
        this.alert = alert;
    }
}
