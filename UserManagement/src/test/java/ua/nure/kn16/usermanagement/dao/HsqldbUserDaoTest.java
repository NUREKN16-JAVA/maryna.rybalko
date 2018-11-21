package ua.nure.kn16.usermanagement.dao;
import nure_ua_kn16_rybalko.User;
import ua.nure.kn16.rybalko.usermanagement.dao.ConnectionFactory;
import ua.nure.kn16.rybalko.usermanagement.dao.ConnectionFactoryImplementation;
import ua.nure.kn16.rybalko.usermanagement.dao.DatabaseException;
import ua.nure.kn16.rybalko.usermanagement.dao.UserDAO;
import ua.nure.kn16.rybalko.usermanagement.dao.HsqldbUserDao;

import java.util.Collection;
import java.util.Date;
import org.dbunit.DatabaseTestCase;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.XmlDataSet;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class HsqldbUserDaoTest extends DatabaseTestCase {
    private User user;
    private UserDAO dao;
    private ConnectionFactory connectionFactory;
    private static final Long ID = 0L;

    @Before
    public void setUp() throws Exception {
        getConnection();
        user = new User("Ivan","Ivanov",new Date());
        dao = new HsqldbUserDao(connectionFactory);
    }

    @After
    public void tearDown() throws Exception {

    }

    /**
     * Tests creation of new user with specified firstName, lastName, dateOfBirth
     * @throws DatabaseException
     */
    @Test
    public void testCreate() throws DatabaseException {
        assertNull(user.getId());
        User userResult = dao.create(user);
        assertNotNull(userResult);
        assertNotNull(userResult.getId());
        assertEquals(user.getFirstName(),userResult.getFirstName());
        assertEquals(user.getLastName(),userResult.getLastName());
        assertEquals(user.getDateOfBirth(),userResult.getDateOfBirth());
    }

    /**
     * Test for updating user record in DB method
     * @throws DatabaseException
     */
    @Test
    public void testUpdate() throws DatabaseException {
        User user = new User("Ivan","Rybalko",new Date());
        user.setId(0L);
        dao.update(user);
        User testUser = dao.find(user.getId());
        assertNotNull(testUser);
        assertEquals(user.getLastName(), testUser.getLastName());
    }

    /**
     * Test for user deleting method
     * @throws DatabaseException
     */
    @Test
    public void testDelete() throws DatabaseException {
        User testUser = new User(ID, "Ivan", "Ivanov", new Date());
        dao.delete(testUser);
        assertNull(dao.find(ID));
    }
    
    /**
     * Tests search for all users in DB
     * @throws DatabaseException
     */
    @Test
    public void testFindAll() throws DatabaseException {
        User userResult = dao.create(user);
        int expectedSize = 3;
        Collection<?> collection = dao.findAll();
        assertNotNull("Object reference not set to an instance of an object", collection);
        assertEquals("Collection size comparison", expectedSize, collection.size());
    }
    
    /**
     * Tests search for determined user with specific id
     * @throws DatabaseException
     */
    @Test
    public void testFind () throws DatabaseException {
        User testUser = dao.find(ID);
        assertNotNull(testUser);
        assertEquals( user.getFirstName(),testUser.getFirstName());
        assertEquals(user.getLastName(),testUser.getLastName());
    }

    @Override
    protected IDatabaseConnection getConnection() throws Exception {
        connectionFactory = new ConnectionFactoryImplementation
        		("org.hsqldb.jdbcDriver", "jdbc:hsqldb:file:db/Java_1_University","sa","");
       
        return new DatabaseConnection(connectionFactory.createConnection());
    }

    @Override
    protected IDataSet getDataSet() throws Exception {
        IDataSet dataSet = new XmlDataSet(getClass().getClassLoader().getResourceAsStream("usersDataSet.xml"));
        
        return dataSet;
    }

}

