package edu.vinaenter.constants.SQL;

public class UserSQLConstant {
	public static final String SELECT_ALL = "SELECT * FROM users ORDER BY uid DESC ";
	
	public static final String FIND_BY_USER = "SELECT * FROM users WHERE username LIKE ?";
	
	public static final String COUNT_ALL = "SELECT COUNT(*) FROM users";
	
	public static final String ADD_USER = "INSERT INTO users(username,password,fullname) VALUES(?,?,?)";
	
	public static final String DEL_BY_ID = "DELETE FROM users WHERE uid = ?";
	
	public static final String SELECT_BY_ID = "SELECT * FROM users WHERE uid = ? ";
	
	public static final String UPDATE_USER = "UPDATE users SET username = ?, password = ?, fullname = ? WHERE uid = ?";
}
