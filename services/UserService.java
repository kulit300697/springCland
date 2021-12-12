package edu.vinaenter.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.vinaenter.daos.UserDAO;
import edu.vinaenter.models.User;
import edu.vinaenter.utils.StringUtil;

@Service
public class UserService {
	@Autowired
	private UserDAO userDAO;
	private StringUtil stringUtil = new StringUtil();
	public List<User> selectAll(){
		return userDAO.selectAll();
	}
	public int countAll() {
		return userDAO.countAll();
	}
	public User findByUser(String username) {
		return userDAO.findByUser(username);
	}
	public boolean itUser(String username, String password) { //Sử dụng cách này vì không thể equals từ Mảng được
		User user = userDAO.findByUser(username);
		if (user!=null && user.getPassword().equals(password)) {
			return true;
		}
		return false;
	}
	public boolean hasUser(String username) { //Kiểm tra trùng username
		User user = userDAO.findByUser(username);
		if (user == null) {
			return true;
		}
		return false;
	}
	public int add(User t) {
		return userDAO.add(t);
	}
	public int del(int id) {
		return userDAO.del(id);
	}
	public User selectByID(int id) {
		return userDAO.selectByID(id);
	}
	public int edit(User t) {
		User user = userDAO.selectByID(t.getUid());
		if (t.getPassword().equals("")) {
			t.setPassword(user.getPassword());
		} else {
			t.setPassword(stringUtil.md5(t.getPassword()));
		}
		return userDAO.edit(t);
	}
}
