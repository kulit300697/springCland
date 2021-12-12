package edu.vinaenter.constants.SQL;

public class CategorySQLConstant {
	public static final String SELECT_ALL = "SELECT * FROM categories ORDER BY cid DESC";
	
	public static final String SELECT_CAT_HOT = "SELECT * FROM categories ORDER BY RAND() DESC LIMIT 3";
	
	public static final String ADD_CAT = "INSERT INTO categories(cname) VALUES(?)";
	
	public static final String DEL_BY_ID = "DELETE FROM categories WHERE cid = ?";
	
	public static final String COUNT_ALL = "SELECT COUNT(*) FROM categories";
	
	public static final String SELECT_BY_ID = "SELECT * FROM categories WHERE cid = ?";
	
	public static final String UPDATE_CAT = "UPDATE categories SET cname = ? WHERE cid = ?";
}
