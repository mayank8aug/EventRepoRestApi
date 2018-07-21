package com.eventrepoapi.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.eventrepoapi.model.Actor;
import com.eventrepoapi.model.Event;

@Repository
public class EventRepositoryImpl implements EventRepository {

	@Autowired
	JdbcTemplate jdbcTemplate;
	
	public List<Event> getAllEvents() {
		return jdbcTemplate.query("select * from event order by id asc", new EventRowMapper());
	}
	
	public Event getEventById(Long id) {
		return jdbcTemplate.queryForObject(
				"select * from event where id=?", 
				new Object[]{id}, new EventRowMapper());
	}
	
	public List<Event> getAllEventsByActor(Long actorId) {
		return jdbcTemplate.query("select * from event where actorid=? order by id asc", new Object[] {
	        actorId
	    }, new EventRowMapper());
	}
	
	public int deleteById(Long id) {
	    return jdbcTemplate.update("delete from event where id=?", new Object[] {
	        id
	    });
	}
	
	public int deleteAllEvents() {
		return jdbcTemplate.update("delete from event");
	}
	
	public int insert(Event event) throws SQLException{
	    return jdbcTemplate.update("insert into event (id, type, actorid, repoid, created_at) " + "values(?, ?, ?, ?, ?)",
	        new Object[] {
	        		event.getId(), event.getType(), event.getActorId(), event.getRepoId(), event.getCreatedAt()
	        });
	}
	
	class EventRowMapper implements RowMapper<Event> {
	    @Override
	    public Event mapRow(ResultSet rs, int rowNum) throws SQLException {
	    	Event event = new Event();
	    	event.setId(rs.getLong("id"));
	    	event.setActorId(rs.getLong("actorid"));
	    	event.setRepoId(rs.getLong("repoid"));
	    	event.setCreatedAt(rs.getTimestamp("created_at"));
	    	event.setType(rs.getString("type"));
	        return event;
	    }
	}
}
