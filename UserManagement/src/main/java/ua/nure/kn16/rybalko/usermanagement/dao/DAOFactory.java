package ua.nure.kn16.rybalko.usermanagement.dao;
import java.io.IOException;
import java.util.Properties;

public class DAOFactory {
    public static final String USER_DAO_CONST = "dao.ua.nure.kn16.rybalko.usermanagement.dao.UserDAO";
    private final Properties properties;
    private final static DAOFactory DAO_FACTORY_INSTANCE = new DAOFactory();

    public static DAOFactory getInstance() {
        return DAO_FACTORY_INSTANCE;
    }

    private DAOFactory() {
        properties = new Properties();
        try 
        {
            properties.load(getClass().getClassLoader().getResourceAsStream("settings.properties"));
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }

    private ConnectionFactory getConnectionFactory() {      
    	String url = properties.getProperty("connection.url");
    	String driver = properties.getProperty("connection.driver");
    	String password = properties.getProperty("connection.password");
        String user = properties.getProperty("connection.user");

        return new ConnectionFactoryImplementation(driver, url, user, password);
    }
    
    @SuppressWarnings("deprecation")
	public UserDAO getUserDAO(){
        UserDAO result = null;
        try {
            Class<?> classUserDao = Class.forName(properties.getProperty(USER_DAO_CONST));
            result = (UserDAO) classUserDao.newInstance();
            result.setConnectionFactory(getConnectionFactory());
        } catch (Exception e) {
            throw new RuntimeException();
        }
        return result;
    }
}