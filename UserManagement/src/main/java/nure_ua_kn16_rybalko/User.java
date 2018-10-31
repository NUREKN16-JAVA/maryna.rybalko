package nure_ua_kn16_rybalko;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

public class User implements Serializable {
	
	private static final long serialVersionUID = 3580147;
	private Long id;
	private String firstName;
	private String lastName;
	private Date dateOfBirth;

	public User() {
		super();
		//TODO ADD INPLEMENTATION
	}
	
	public User(Long id, String firstName, String lastName, Date dateObBirth) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.dateOfBirth = dateObBirth;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateObBirth) {
		this.dateOfBirth = dateObBirth;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dateOfBirth == null) ? 0 : dateOfBirth.hashCode());
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (dateOfBirth == null) {
			if (other.dateOfBirth != null)
				return false;
		} else if (!dateOfBirth.equals(other.dateOfBirth))
			return false;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", firstNane=" + firstName + ", lastName=" + lastName + ", dateObBirth=" + dateOfBirth
				+ "]";
	}

	public String getFullName() {
		return new StringBuilder(this.getFirstName()).append(",").append(this.getLastName()).toString();
	}
	
public int getAge() {
		
		Calendar dateOfBirth = Calendar.getInstance();
		Calendar today = Calendar.getInstance();
		
		dateOfBirth.setTime(getDateOfBirth());

		int age = today.get(Calendar.YEAR) - dateOfBirth.get(Calendar.YEAR);

		if (dateOfBirth.get(Calendar.MONTH) > today.get(Calendar.MONTH)) {
			age--;
		} else if (dateOfBirth.get(Calendar.MONTH) == today.get(Calendar.MONTH)) {

			if (dateOfBirth.get(Calendar.DAY_OF_MONTH) > today.get(Calendar.DAY_OF_MONTH)) {
				age--;
			}
		}
		return age;
	}
}
