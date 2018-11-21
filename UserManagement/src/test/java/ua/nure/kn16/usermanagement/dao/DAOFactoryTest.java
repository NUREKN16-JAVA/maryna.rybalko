package ua.nure.kn16.usermanagement.dao;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ua.nure.kn16.rybalko.usermanagement.dao.DAOFactory;
import ua.nure.kn16.rybalko.usermanagement.dao.UserDAO;

import static org.junit.Assert.*;

public class DAOFactoryTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testGetUserDao() {
        try{
        DAOFactory daoFactory = DAOFactory.getInstance();
        assertNotNull("Instance of DaoFactory is null", daoFactory);
        UserDAO userDao = daoFactory.getUserDAO();
        assertNotNull("Instance of UserDao is null", userDao);
        }catch (RuntimeException e){
            fail(e.toString());
        }
    }
}