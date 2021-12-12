package edu.vinaenter.constants.SQL;

import edu.vinaenter.constants.Defines;

public class LandSQLConstant {
	public static final String SELECT_ALL = "SELECT * FROM lands ORDER BY lid DESC";
	
	public static final String SELECT_DATE_NEW = "SELECT * FROM lands ORDER BY date_create DESC LIMIT "+ Defines.SELECT_LIMIT;
	
	public static final String SELECT_COUNT_VIEW = "SELECT * FROM lands ORDER BY count_views DESC LIMIT "+ Defines.SELECT_LIMIT;
	
	public static final String SELECT_BY_CID = "SELECT * FROM lands WHERE cid = ?";
	
	public static final String SELECT_SLIDER = "SELECT * FROM lands ORDER BY RAND() LIMIT "+ Defines.SELECT_SLIDER;
	
	public static final String COUNT_ALL = "SELECT COUNT(*) FROM lands";
	
	public static final String ADD_LAND = "INSERT INTO lands(lname,description,date_create,cid,picture,area,address) VALUES(?,?,?,?,?,?,?)";
	
	public static final String DEL_LAND = "DELETE FROM lands WHERE lid = ?";
	
	public static final String SELECT_BY_LID = "SELECT * FROM lands WHERE lid = ?";
}
