package nure_ua_kn16_rybalko.db;

import java.sql.Connection;

public interface ConnectionFactory {
	Connection createConnection()throws DatabaseException;

}
