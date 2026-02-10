package contact;

public class Contact {
	
	private final String contactID;	// Not updatable
	private String firstName;
	private String lastName;
	private String phone;
	private String address;
	
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
	
	// Getters
	public String getContactID() {return contactID;}
	public String getFirstName() {return firstName;}
	public String getLastName() {return lastName;}
	public String getPhone() {return phone;}
	public String getAddress() {return address;}
	
	// Setters for updatable fields
	public void setFirstName(String firstName) {
	    if (firstName == null || firstName.length() > 10){throw new IllegalArgumentException("Invalid first name");}
	    this.firstName = firstName;
	}
	public void setLastName(String lastName) {
	    if (lastName == null || lastName.length() > 10){throw new IllegalArgumentException("Invalid last name");}
	    this.lastName = lastName;
	}
	public void setPhone(String phone) {
	    if (phone == null || !phone.matches("\\d{10}")) {throw new IllegalArgumentException("Invalid phone");}
	    this.phone = phone;
	}
	public void setAddress(String address) {
	    if (address == null || address.length() > 30){throw new IllegalArgumentException("Invalid address");}
	    this.address = address;
	}
}