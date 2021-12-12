package edu.vinaenter.constants.SQL;

public class ContactSQLConstant {
	public static final String SELECT_ALL = "SELECT * FROM vnecontact ORDER BY cid DESC";
	
	public static final String COUNT_ALL = "SELECT COUNT(*) FROM vnecontact";
	
	public static final String ADD_CONTACT = "INSERT INTO vnecontact(fullname, email, subject, content) VALUES(?,?,?,?)";
	
	public static final String DEL_CONTACT = "DELETE FROM vnecontact WHERE cid = ?";
}
