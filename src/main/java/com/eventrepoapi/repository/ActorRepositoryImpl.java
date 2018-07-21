package com.eventrepoapi.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.eventrepoapi.model.Actor;
import com.eventrepoapi.model.Repo;
import com.eventrepoapi.repository.EventRepositoryImpl.EventRowMapper;

@Repository
public class ActorRepositoryImpl implements ActorRepository {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public List<Actor> getAllActors() {
		return jdbcTemplate.query("select actor.id, actor.login, actor.avatar, actor.max_streak, actor.running_streak, count(event.id) as eventcount from actor inner join event on actor.id = event.actorid group by actor.id order by eventcount desc ", new ActorRowMapper());
	}
	
	public Actor findById(Long id) {
		return jdbcTemplate.queryForObject(
	            "select * from actor where id=?",
	            new Object[]{id}, new ActorRowMapper());
	}
	
	public int deleteById(Long id) {
	    return jdbcTemplate.update("delete from actor where id=?", new Object[] {
	        id
	    });
	}
	
	public int insert(Actor actor) {
	    return jdbcTemplate.update("insert into actor (id, login, avatar, max_streak, running_streak) " + "values(?, ?, ?, ?, ?)",
	        new Object[] {
	        		actor.getId(), actor.getLogin(), actor.getAvatar(), actor.getMaxStreak(), actor.getRunningStreak()
	        });
	}
	
	public int updateAvatar(Long id, String url) {
		return jdbcTemplate.update("update actor set avatar = ? where id = ?", url, id);
	}
	class ActorRowMapper implements RowMapper<Actor> {
	    @Override
	    public Actor mapRow(ResultSet rs, int rowNum) throws SQLException {
	    	Actor actor = new Actor();
	    	actor.setId(rs.getLong("id"));
	    	actor.setLogin(rs.getString("login"));
	    	actor.setAvatar(rs.getString("avatar"));
	    	actor.setMaxStreak(rs.getLong("max_streak"));
	    	actor.setRunningStreak(rs.getLong("running_streak"));
	        return actor;
	    }
	}
}
