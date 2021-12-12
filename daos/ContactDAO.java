package edu.vinaenter.daos;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import edu.vinaenter.constants.SQL.ContactSQLConstant;
import edu.vinaenter.models.Contact;

@Repository
public class ContactDAO extends AbstractDao<Contact> {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Override
	public List<Contact> selectAll() {
		return jdbcTemplate.query(ContactSQLConstant.SELECT_ALL, new BeanPropertyRowMapper<Contact>(Contact.class));
	}

	@Override
	public Contact selectByID(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int add(Contact t) {
		return jdbcTemplate.update(ContactSQLConstant.ADD_CONTACT, t.getFullname(), t.getEmail(), t.getSubject(), t.getContent());
	}

	@Override
	public int del(int id) {
		return jdbcTemplate.update(ContactSQLConstant.DEL_CONTACT, id);
	}

	@Override
	public int edit(Contact t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Contact> search(String search) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int countAll() {
		return jdbcTemplate.queryForObject(ContactSQLConstant.COUNT_ALL, Integer.class);
	}

}
