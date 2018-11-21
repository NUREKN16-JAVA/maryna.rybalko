package ua.nure.kn16.rybalko.usermanagement.dao;
import java.sql.Connection;

public interface ConnectionFactory {
	
	Connection createConnection() throws DatabaseException;
}
