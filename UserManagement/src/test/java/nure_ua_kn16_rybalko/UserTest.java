package nure_ua_kn16_rybalko;

import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class UserTest {

	private static final String FIRST_NAME = "Rybalko";
	private static final String LAST_NAME = "Marinka";
	private static final long ID = 1L;
	private User user;

	// true for 11-Oct-2018
	private static final int CURRENT_YEAR = 2018;
	private static final int YEAR_OF_BIRTH = 1971;

	// For the case where the birthday has already passed, but the month is still going 
	// Constant, which determines the age by the current year and year of birth of the user
	private static final int ETALON_AGE_CASE1 = CURRENT_YEAR - YEAR_OF_BIRTH;
	private static final int DAY_OF_BIRTH_CASE1 = 7;
	private static final int MOUNTH_OF_BIRTH_CASE1 = Calendar.OCTOBER;

	//For the case where the birthday has already passed, and a month has passed
	private static final int DAY_OF_BIRTH_CASE2 = 19;
	private static final int MOUNTH_OF_BIRTH_CASE2 = Calendar.JULY;

	//For the occasion where is the birthday on this day
	private static final int DAY_OF_BIRTH_CASE3 = 15;
	private static final int MOUNTH_OF_BIRTH_CASE3 = Calendar.OCTOBER;

	//For the case where the birthday is in this month, but has not yet come
	private static final int ETALON_AGE_2 = CURRENT_YEAR - YEAR_OF_BIRTH - 1;
	private static final int DAY_OF_BIRTH_CASE4 = 30;
	private static final int MOUNTH_OF_BIRTH_CASE4 = Calendar.OCTOBER;

	// For the case where the month and day of birth have not yet come
	private static final int DAY_OF_BIRTH_CASE5 = 22;
	private static final int MOUNTH_OF_BIRTH_CASE5 = Calendar.DECEMBER;

	@Before
	public void setUp() throws Exception {
		user = new User(ID, FIRST_NAME, LAST_NAME, new Date());
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetFullName() {
		assertEquals("Rybalko,Marinka", user.getFullName());
	}

	
	@Test
	public void ifDayOFBirthHasAlreadyPassedButMounthStillGoingItShouldReturnCorrectAge() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(YEAR_OF_BIRTH, MOUNTH_OF_BIRTH_CASE1, DAY_OF_BIRTH_CASE1);
		Date dateOfBirth = calendar.getTime();
		user.setDateOfBirth(dateOfBirth);
		assertEquals(ETALON_AGE_CASE1, user.getAge());
	}

	@Test
	public void ifDayOFBirthAndMounthHaveAlreadyPassedItShouldReturnCorrectAge() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(YEAR_OF_BIRTH, MOUNTH_OF_BIRTH_CASE2, DAY_OF_BIRTH_CASE2);
		Date dateOfBirth = calendar.getTime();
		user.setDateOfBirth(dateOfBirth);
		assertEquals(ETALON_AGE_CASE1, user.getAge());
	}

	@Test
	public void ifDayOFBirthIsTodayItShouldReturnCorrectAge() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(YEAR_OF_BIRTH, MOUNTH_OF_BIRTH_CASE3, DAY_OF_BIRTH_CASE3);
		Date dateOfBirth = calendar.getTime();
		user.setDateOfBirth(dateOfBirth);
		assertEquals(ETALON_AGE_CASE1, user.getAge());
	}

	@Test
	public void ifDayOFBirthInThisMounthButNotComeItShouldReturnCorrectAge() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(YEAR_OF_BIRTH, MOUNTH_OF_BIRTH_CASE4, DAY_OF_BIRTH_CASE4);
		Date dateOfBirth = calendar.getTime();
		user.setDateOfBirth(dateOfBirth);
		assertEquals(ETALON_AGE_2, user.getAge());
	}

	@Test
	public void ifDayOFBirthAndMounthHaveNotComeYetItShouldReturnCorrectAge() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(YEAR_OF_BIRTH, MOUNTH_OF_BIRTH_CASE5, DAY_OF_BIRTH_CASE5);
		Date dateOfBirth = calendar.getTime();
		user.setDateOfBirth(dateOfBirth);
		assertEquals(ETALON_AGE_2, user.getAge());
	}

}
