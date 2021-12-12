package edu.vinaenter.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.vinaenter.daos.ContactDAO;
import edu.vinaenter.models.Contact;

@Service
public class ContactService {
	@Autowired
	private ContactDAO contactDAO;
	public List<Contact> selectAll() {
		return contactDAO.selectAll();
	}
	public int countAll() {
		return contactDAO.countAll();
	}
	public int add(Contact t) {
		return contactDAO.add(t);
	}
	public int del(int id) {
		return contactDAO.del(id);
	}
}
