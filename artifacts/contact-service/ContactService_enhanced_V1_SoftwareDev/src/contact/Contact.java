package contact;

/**
 * Represents a single contact with validated personal information.
 * 
 * A Contact object enforces data integrity by validating all fields
 * at construction time and restricting updates to specific fields.
 */
public class Contact {
	
	private final String contactID;	// Not updatable
	private String firstName;
	private String lastName;
	private String phone;
	private String address;
	
	/**
	 * Creates a new Contact with validated fields.
	 *
	 * @param contactID unique identifier for the contact (max 10 characters)
	 * @param firstName contact's first name (max 10 characters)
	 * @param lastName contact's last name (max 10 characters)
	 * @param phone contact's phone number (exactly 10 digits)
	 * @param address contact's street address (max 30 characters)
	 * @throws IllegalArgumentException if any parameter is invalid
	 */
	public Contact(String contactID, String firstName, String lastName, String phone, String address) { 
		if(contactID == null || contactID.length()>10) {throw new IllegalArgumentException("Invalid contact ID");}
		this.contactID = contactID;

		if(firstName == null || firstName.length()>10) {throw new IllegalArgumentException("Invalid first name");}
		this.firstName = firstName;

		if(lastName == null || lastName.length()>10) {throw new IllegalArgumentException("Invalid last name");}
		this.lastName = lastName;

		if(phone == null || !phone.matches("\\d{10}")) {throw new IllegalArgumentException("Invalid phone number");}
		this.phone = phone;

		if(address == null || address.length()>30) {throw new IllegalArgumentException("Invalid address");}
		this.address = address;
		
	}
	
	// --- Getters ---
	public String getContactID() {return contactID;}
	public String getFirstName() {return firstName;}
	public String getLastName() {return lastName;}
	public String getPhone() {return phone;}
	public String getAddress() {return address;}
	
	/**
	 * Updates the contact's first name.
	 *
	 * @param firstName new first name (up to 10 characters)
	 * @throws IllegalArgumentException if the value is invalid
	 */
	public void setFirstName(String firstName) {
	    if (firstName == null || firstName.length() > 10){throw new IllegalArgumentException("Invalid first name");}
	    this.firstName = firstName;
	}
	/**
	 * Updates the contact's last name.
	 *
	 * @param lastName new last name (up to 10 characters)
	 * @throws IllegalArgumentException if the value is invalid
	 */
	public void setLastName(String lastName) {
	    if (lastName == null || lastName.length() > 10){throw new IllegalArgumentException("Invalid last name");}
	    this.lastName = lastName;
	}
	/**
	 * Updates the contact's phone number.
	 *
	 * @param phone new phone number (exactly 10 digits)
	 * @throws IllegalArgumentException if the value is invalid
	 */
	public void setPhone(String phone) {
	    if (phone == null || !phone.matches("\\d{10}")) {throw new IllegalArgumentException("Invalid phone");}
	    this.phone = phone;
	}
	/**
	 * Updates the contact's address.
	 *
	 * @param address new address (up to 30 characters)
	 * @throws IllegalArgumentException if the value is invalid
	 */
	public void setAddress(String address) {
	    if (address == null || address.length() > 30){throw new IllegalArgumentException("Invalid address");}
	    this.address = address;
	}
}