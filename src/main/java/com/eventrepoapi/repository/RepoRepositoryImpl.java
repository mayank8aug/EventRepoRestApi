package com.eventrepoapi.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.eventrepoapi.model.Event;
import com.eventrepoapi.model.Repo;
import com.eventrepoapi.repository.EventRepositoryImpl.EventRowMapper;

@Repository
public class RepoRepositoryImpl implements RepoRepository{
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	public List<Repo> getAllRepos() {
		return jdbcTemplate.query("select * from repo", new RepoRowMapper());
	}
	
	public Repo getRepoById(Long id) {
		return jdbcTemplate.queryForObject(
				"select * from repo where id=?", 
				new Object[]{id}, new RepoRowMapper());
	}
	
	public int deleteById(Long id) {
	    return jdbcTemplate.update("delete from repo where id=?", new Object[] {
	        id
	    });
	}
	
	public int insert(Repo repo) {
	    return jdbcTemplate.update("insert into repo (id, name, url) " + "values(?, ?, ?)",
	        new Object[] {
	        		repo.getId(), repo.getName(), repo.getUrl()
	        });
	}
	
	class RepoRowMapper implements RowMapper<Repo> {
	    @Override
	    public Repo mapRow(ResultSet rs, int rowNum) throws SQLException {
	    	Repo repo = new Repo();
	    	repo.setId(rs.getLong("id"));
	    	repo.setName(rs.getString("name"));
	    	repo.setUrl(rs.getString("url"));
	        return repo;
	    }
	}
	
}
