package edu.vinaenter.daos;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import edu.vinaenter.constants.SQL.LandSQLConstant;
import edu.vinaenter.models.Land;

@Repository
public class LandDAO extends AbstractDao<Land> {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Override
	public List<Land> selectAll() {
		return jdbcTemplate.query(LandSQLConstant.SELECT_ALL, new BeanPropertyRowMapper<Land>(Land.class));
	}
	
	public List<Land> selectSlider() {
		return jdbcTemplate.query(LandSQLConstant.SELECT_SLIDER, new BeanPropertyRowMapper<Land>(Land.class));
	}
	
	public List<Land> selectAllDateNew() {
		return jdbcTemplate.query(LandSQLConstant.SELECT_DATE_NEW, new BeanPropertyRowMapper<Land>(Land.class));
	}
	
	public List<Land> selectAllCountView() {
		return jdbcTemplate.query(LandSQLConstant.SELECT_COUNT_VIEW, new BeanPropertyRowMapper<Land>(Land.class));
	}
	
	public List<Land> selectByCid(int cid){
		return jdbcTemplate.query(LandSQLConstant.SELECT_BY_CID, new BeanPropertyRowMapper<Land>(Land.class), cid);
	}

	@Override
	public Land selectByID(int id) {
		return jdbcTemplate.queryForObject(LandSQLConstant.SELECT_BY_LID, new BeanPropertyRowMapper<Land>(Land.class), id);
	}

	@Override
	public int add(Land t) {
		return jdbcTemplate.update(LandSQLConstant.ADD_LAND, t.getLname(), t.getDescription(), t.getDate_create(), t.getCid(), t.getPicture(), t.getArea(), t.getAddress());
	}

	@Override
	public int del(int id) {
		return jdbcTemplate.update(LandSQLConstant.DEL_LAND, id);
	}

	@Override
	public int edit(Land t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Land> search(String search) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int countAll() {
		return jdbcTemplate.queryForObject(LandSQLConstant.COUNT_ALL, Integer.class);
	}
	
}
