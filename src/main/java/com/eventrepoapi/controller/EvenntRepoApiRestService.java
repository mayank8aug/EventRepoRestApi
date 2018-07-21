package com.eventrepoapi.controller;

import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.eventrepoapi.model.Actor;
import com.eventrepoapi.model.Event;
import com.eventrepoapi.model.Repo;
import com.eventrepoapi.repository.ActorRepositoryImpl;
import com.eventrepoapi.repository.EventRepositoryImpl;
import com.eventrepoapi.repository.RepoRepositoryImpl;

@Component
public class EvenntRepoApiRestService {
	
	@Autowired
	private EventRepositoryImpl eventRepoImpl;
	
	@Autowired
	private RepoRepositoryImpl repoRepoImpl;
	
	@Autowired
	private ActorRepositoryImpl actorRepoImpl;
	
	public List<Event> getAllEvents() {
		return eventRepoImpl.getAllEvents();
	}
	
	public List<Actor> getAllActors() {
		return actorRepoImpl.getAllActors();
	}
	
	public List<Event> getAllEventsByActor(Long actorId) {
		return eventRepoImpl.getAllEventsByActor(actorId);
	}
	
	public int deleteAllEvents() {
		int deleted = eventRepoImpl.deleteAllEvents();
		if(deleted > 0) {
			ServiceUtil.removeAllEvents();
		}
		return deleted;
	}
	
	public int updateActorAvator(JSONObject jsonObj) {
		Long actorId;
		try {
			actorId = jsonObj.getLong("id");
			if(!ServiceUtil.isActorExists(actorId)) {
				return 404;
			}
			
			return actorRepoImpl.updateAvatar(actorId, jsonObj.getString("avatar_url"));
		} catch (JSONException e) {
			System.out.println("JSON Error");
		}
		return 1;
	}
	
	public int processEvent(JSONObject jsonObj) {
		try {
			Long eventId = jsonObj.getLong("id");
			if(ServiceUtil.isEventExists(eventId)) {
				return 400;
			}
			String eventType = jsonObj.getString("type");
			String createdAt = jsonObj.getString("created_at");
			JSONObject actorJson = jsonObj.getJSONObject("actor");
			JSONObject repoJson = jsonObj.getJSONObject("repo");
		    Timestamp timestamp = Timestamp.valueOf(createdAt);
		    Long actorId = actorJson.getLong("id");
		    Long repoId = repoJson.getLong("id");
			Event event = new Event(eventId, eventType, actorId, repoId, timestamp);
			try {
				if(eventRepoImpl.insert(event) == 1) {
					ServiceUtil.addEvent(eventId);
				}
				
				if(!ServiceUtil.isActorExists(actorId)) {
					Actor actor = new Actor(actorId, actorJson.getString("login"), actorJson.getString("avatar_url"), 1L, 1L);
					if(actorRepoImpl.insert(actor) == 1) {
						ServiceUtil.addActor(actorId);
					}
				} else {
					
				}
				
				if(!ServiceUtil.isRepoExists(repoId)) {
					Repo repo = new Repo(repoId, repoJson.getString("name"), repoJson.getString("url"));
					if(repoRepoImpl.insert(repo) == 1) {
						ServiceUtil.addRepo(repoId);
					}
				}
			} catch(SQLException sqle) {
				return -1;
			}
		} catch (JSONException e) {
			System.out.println("Exception:" + e.getMessage());
		} catch (Exception e) {
			System.out.println("Exception:" + e.getMessage());
		}
		return 1;
	}
}
