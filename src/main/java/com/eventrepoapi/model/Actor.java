package com.eventrepoapi.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name="actor")
public class Actor {
	@Id
    private Long id;
	@Column
    private String login;
	@Column
    private String avatar;
	@Column(name="max_streak")
    private Long maxStreak;
	@Column(name="running_streak")
    private Long runningStreak;

    public Actor() {
    }

    public Actor(Long id, String login, String avatar, Long maxStreak, Long runningStreak) {
        this.id = id;
        this.login = login;
        this.avatar = avatar;
        this.maxStreak = maxStreak;
        this.runningStreak = runningStreak;
    }
    
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getLogin() {
        return login;
    }
    
    public void setLogin(String login) {
        this.login = login;
    }
    
    public String getAvatar() {
        return avatar;
    }
    
    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

	public Long getMaxStreak() {
		return maxStreak;
	}

	public void setMaxStreak(Long maxStreak) {
		this.maxStreak = maxStreak;
	}

	public Long getRunningStreak() {
		return runningStreak;
	}

	public void setRunningStreak(Long runningStreak) {
		this.runningStreak = runningStreak;
	}
}
