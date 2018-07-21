package com.eventrepoapi.controller;

import java.util.HashSet;
import java.util.Set;

public class ServiceUtil {
	static Set repoSet = new HashSet<Long>();
	static Set actorSet = new HashSet<Long>();
	static Set eventSet = new HashSet<Long>();
	
	public static void addRepo(Long id) {
		repoSet.add(id);
	}
	
	public static void addActor(Long id) {
		actorSet.add(id);
	}
	
	public static void addEvent(Long id) {
		eventSet.add(id);
	}
	
	public static void removeAllEvents( ) {
		eventSet.clear();
	}
	
	public static boolean isRepoExists(Long id) {
		return repoSet.contains(id);
	}
	
	public static boolean isActorExists(Long id) {
		return actorSet.contains(id);
	}
	
	public static boolean isEventExists(Long id) {
		return eventSet.contains(id);
	}
}
