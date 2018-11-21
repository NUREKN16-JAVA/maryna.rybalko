package ua.nure.kn16.rybalko.usermanagement.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactoryImplementation implements ConnectionFactory {
private String driver = "org.hsqldb.jdbcDriver";
private String url = "jdbc:hsqldb:file:db/usermanagement";
private String user = "sa";
private String password = "";
	
public ConnectionFactoryImplementation(String driver, String url, String user, String password) {
    this.driver = driver;
    this.url = url;
    this.user = user;
    this.password = password;
}

	@Override
	public Connection createConnection() throws DatabaseException {
		// TODO Auto-generated method stub
		try {
		Class.forName(driver);
		} catch(ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
		try {
		return DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			throw new DatabaseException(e);
		}
	}
}
