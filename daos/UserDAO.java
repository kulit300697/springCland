package edu.vinaenter.daos;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import edu.vinaenter.constants.SQL.UserSQLConstant;
import edu.vinaenter.models.User;

@Repository
public class UserDAO extends AbstractDao<User> {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public List<User> selectAll() {
		return jdbcTemplate.query(UserSQLConstant.SELECT_ALL, new BeanPropertyRowMapper<User>(User.class));
	}
	
	public User findByUser(String username) {
		try {
			return jdbcTemplate.queryForObject(UserSQLConstant.FIND_BY_USER, new BeanPropertyRowMapper<User>(User.class), username);
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public User selectByID(int id) {
		return jdbcTemplate.queryForObject(UserSQLConstant.SELECT_BY_ID, new BeanPropertyRowMapper<User>(User.class), id);
	}

	@Override
	public int add(User t) {
		return jdbcTemplate.update(UserSQLConstant.ADD_USER, t.getUsername(), t.getPassword(), t.getFullname());
	}

	@Override
	public int del(int id) {
		return jdbcTemplate.update(UserSQLConstant.DEL_BY_ID, id);
	}

	@Override
	public int edit(User t) {
		return jdbcTemplate.update(UserSQLConstant.UPDATE_USER, t.getUsername(), t.getPassword(), t.getFullname(), t.getUid());
	}

	@Override
	public List<User> search(String search) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int countAll() {
		return jdbcTemplate.queryForObject(UserSQLConstant.COUNT_ALL, Integer.class);
	}
	
}
