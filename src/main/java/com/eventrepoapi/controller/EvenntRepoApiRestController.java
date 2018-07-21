package com.eventrepoapi.controller;

import java.util.List;
import java.util.Map;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.eventrepoapi.model.Actor;
import com.eventrepoapi.model.Event;


@RestController
public class EvenntRepoApiRestController {
	
	@Autowired
	private EvenntRepoApiRestService service;
	
	@RequestMapping(value = "/events", method = RequestMethod.GET)
    public ResponseEntity<List<Event>> listAllEvents() {
        List<Event> events = service.getAllEvents();
        if (events.isEmpty()) {
            return new ResponseEntity(HttpStatus.OK);
        }
        return new ResponseEntity<List<Event>>(events, HttpStatus.OK);
    }
	
	@GetMapping("/events/actors/{actorID}")
    public ResponseEntity<List<Event>> listAllEventsByActor(@PathVariable String actorID) {
		Long actor = Long.parseLong(actorID);
		if(!ServiceUtil.isActorExists(actor)) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
        List<Event> events = service.getAllEventsByActor(actor);
        if (events.isEmpty()) {
            return new ResponseEntity(HttpStatus.OK);
        }
        return new ResponseEntity<List<Event>>(events, HttpStatus.OK);
    }
	
	@RequestMapping(value = "/erase", method = RequestMethod.DELETE)
    public ResponseEntity deleteAllEvents() {
        int deleted = service.deleteAllEvents();
        return new ResponseEntity(HttpStatus.OK);
    }
	
	@RequestMapping(value = "/events", method = RequestMethod.POST)
	public ResponseEntity process(@RequestBody Map<String, Object> eventData) throws Exception {
		  JSONObject jsonObj = new JSONObject(eventData);
		  int processed = service.processEvent(jsonObj);
		  if(processed == 400) {
			  return new ResponseEntity(HttpStatus.BAD_REQUEST);
		  } else if(processed == 1) {
			  return new ResponseEntity(HttpStatus.CREATED);
		  }
		  return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@RequestMapping(value = "/actors", method = RequestMethod.PUT)
	public ResponseEntity updatedAvatarUrl(@RequestBody Map<String, Object> actorData) throws Exception {
		  JSONObject jsonObj = new JSONObject(actorData);
		  int processed = service.updateActorAvator(jsonObj);
		  if(processed == 404) {
			  return new ResponseEntity(HttpStatus.NOT_FOUND);
		  } else if(processed == 1) {
			  return new ResponseEntity(HttpStatus.OK);
		  }
		  return new ResponseEntity(HttpStatus.OK);
	}
	
	@RequestMapping(value = "/actors", method = RequestMethod.GET)
    public ResponseEntity<List<Actor>> listAllActors() {
        List<Actor> actors = service.getAllActors();
        if (actors.isEmpty()) {
            return new ResponseEntity(HttpStatus.OK);
        }
        return new ResponseEntity<List<Actor>>(actors, HttpStatus.OK);
    }
}
