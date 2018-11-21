package ua.nure.kn16.rybalko.usermanagement.dao;

import java.sql.SQLException;

public class DatabaseException extends Exception {
	
	private static final long serialVersionUID = -583430147;
	
	public DatabaseException(SQLException e)
	{
		super(e);
	}
	
	public DatabaseException(String string)
	{
		super(string);
	}
}
