package ua.nure.kn16.rybalko.usermanagement.dao;
import java.util.Collection;
import java.util.List;
import nure_ua_kn16_rybalko.User;
public interface UserDAO {
	     /**
		 * Add user to DB table USER
		 * @param user with null id field
		 * @return user modified record example with DB auto generated field
		 */
		public User create(final User user) throws DatabaseException;
		
		/**
		 * Update specified user in DB table USER
		 * @param user with null updated properties
		 */
		public void update(final User user) throws DatabaseException;;
		
	    /**
	     * Deletes user from database with specific id
	     * @param user
	     */
	    public void delete(final User user) throws DatabaseException;
		
		/**
		 * Find user in DB table USER
		 * @param user with specified id
		 * @return user who has specified id
		 * @throws DatabaseException 
		 */
	    public User find(final Long id) throws DatabaseException;
		
		/**
		 * Find all users in DB table USER
		 * @return all users from DB table USER
		 */
	    public Collection<User> findAll() throws DatabaseException;
		
	    /**
	     * Sets connection factory by injection
	     * @param connectionFactory
	     */
	    public void setConnectionFactory(ConnectionFactory connectionFactory);
}
