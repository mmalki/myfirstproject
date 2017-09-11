package com.software2ms.myfirstproject.dto.rest;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.software2ms.myfirstproject.utils.CustomLocalDateTimeDeserializer;
import com.software2ms.myfirstproject.utils.CustomLocalDateTimeSerializer;

import java.time.LocalDateTime;

public class Todo {
    @JsonProperty("id")
    private int id;
    @JsonProperty("user")
    private String user;
    @JsonProperty("desc")
    private String desc;
    //@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @JsonSerialize(using = CustomLocalDateTimeSerializer.class)
    @JsonDeserialize(using = CustomLocalDateTimeDeserializer.class)
    @JsonProperty("targetDate")
    private LocalDateTime targetDate;
    @JsonProperty("done")
    private boolean isDone;


    public Todo( @JsonProperty("id") int id, @JsonProperty("user") String user,@JsonProperty("desc") String desc,@JsonProperty("targetDate") LocalDateTime targetDate,
                 @JsonProperty("done")boolean isDone) {
        super();
        this.id = id;
        this.user = user;
        this.desc = desc;
        this.targetDate = targetDate;
        this.isDone = isDone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public LocalDateTime getTargetDate() {
        return targetDate;
    }

    public void setTargetDate(LocalDateTime targetDate) {
        this.targetDate = targetDate;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + id;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Todo other = (Todo) obj;
        return id == other.id;
    }

    @Override
    public String toString() {
        return String.format(
                "Todo [id=%s, user=%s, desc=%s, targetDate=%s, isDone=%s]", id,
                user, desc, targetDate, isDone);
    }

}