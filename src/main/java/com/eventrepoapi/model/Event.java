package com.eventrepoapi.model;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name="event")
public class Event {
	@Id
    private Long id;
	@Column
    private String type;
	@Column
    private Long actorid;
	@Column
    private Long repoid;
	@Column(name="created_at")
    private Timestamp createdAt;

    public Event() {
    }

    public Event(Long id, String type, Long actorid, Long repoid, Timestamp createdAt) {
        this.id = id;
        this.type = type;
        this.actorid = actorid;
        this.repoid = repoid;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getActorId() {
        return actorid;
    }

    public void setActorId(Long actorId) {
        this.actorid = actorId;
    }

    public Long getRepoId() {
        return repoid;
    }

    public void setRepoId(Long repoId) {
        this.repoid = repoId;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }
}
