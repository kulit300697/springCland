package edu.vinaenter.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.vinaenter.daos.CategoryDAO;
import edu.vinaenter.models.Category;

@Service
public class CategoryService {
	@Autowired
	private CategoryDAO categoryDAO;
	public List<Category> selectAll() {
		return categoryDAO.selectAll();
	}
	public List<Category> selectCatHot() {
		return categoryDAO.selectCatHot();
	}
	public int del(int id) {
		return categoryDAO.del(id);
	}
	public int countAll() {
		return categoryDAO.countAll();
	}
	public int add(Category t) {
		return categoryDAO.add(t);
	}
	public Category selectByID(int id) {
		return categoryDAO.selectByID(id);
	}
	public int edit(Category t) {
		return categoryDAO.edit(t);
	}
}
