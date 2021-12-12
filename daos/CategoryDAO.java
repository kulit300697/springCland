package edu.vinaenter.daos;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import edu.vinaenter.constants.SQL.CategorySQLConstant;
import edu.vinaenter.models.Category;

@Repository
public class CategoryDAO extends AbstractDao<Category> {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public List<Category> selectAll() {
		return jdbcTemplate.query(CategorySQLConstant.SELECT_ALL, new BeanPropertyRowMapper<Category>(Category.class));
	}
	
	public List<Category> selectCatHot() {
		return jdbcTemplate.query(CategorySQLConstant.SELECT_CAT_HOT, new BeanPropertyRowMapper<Category>(Category.class));
	}

	@Override
	public Category selectByID(int id) {
		return jdbcTemplate.queryForObject(CategorySQLConstant.SELECT_BY_ID, new BeanPropertyRowMapper<Category>(Category.class), id);
	}

	@Override
	public int add(Category t) {
		return jdbcTemplate.update(CategorySQLConstant.ADD_CAT,t.getCname());
	}

	@Override
	public int del(int id) {
		return jdbcTemplate.update(CategorySQLConstant.DEL_BY_ID, id);
	}

	@Override
	public int edit(Category t) {
		return jdbcTemplate.update(CategorySQLConstant.UPDATE_CAT, t.getCname(), t.getCid());
	}

	@Override
	public List<Category> search(String search) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int countAll() {
		return jdbcTemplate.queryForObject(CategorySQLConstant.COUNT_ALL, Integer.class);
	}

}
