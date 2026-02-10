package contact;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;

/**
 * Provides business logic for managing Contact objects.
 *
 * This service enforces validation, uniqueness, and consistent
 * behavior for adding, updating, and deleting contacts.
 */
public class ContactService {

    private HashMap<String, Contact> contacts = new HashMap<>();

	/**
	 * Loads a full list of contacts into the service, replacing existing data.
	 *
	 * @param loaded list of contacts to load
	 * @throws IllegalArgumentException if the list is null
	 */
    public void loadAll(List<Contact> loaded) {
        if (loaded == null) {
            throw new IllegalArgumentException("loaded contacts cannot be null");
        }
        contacts.clear();
        // Reuse addContact to enforce uniqueness and null checks
        for (Contact c : loaded) {
            addContact(c);
        }
    }

	/**
	 * Returns a defensive copy of all stored contacts.
	 *
	 * @return list of contacts
	 */
    public List<Contact> getAllContacts() {
        return new ArrayList<>(contacts.values());
    }

	/**
	 * Adds a new contact to the service.
	 *
	 * @param contact contact to add
	 * @throws IllegalArgumentException if the contact is null or the ID already exists
	 */
    public void addContact(Contact contact) {
		// Throw exception if the contact info is null
        if (contact == null) {
            throw new IllegalArgumentException("contact cannot be null");
        } 
		
		// Extract the ID from the contact object to use as a key
		String id = contact.getContactID();
		
		// Throw exception if the contact ID exists in the HashMap
		if (contacts.containsKey(id)){
			throw new IllegalArgumentException("contact ID already exists");
		}
		
		// Save the contact successfully
        contacts.put(id, contact);
    }

	/**
	 * Deletes an existing contact by ID.
	 *
	 * @param id contact ID to delete
	 * @throws IllegalArgumentException if the ID is null, empty, or not found
	 */
    public void deleteContact(String id) {
		// Throw exception if contact ID does not exist
		if (!contacts.containsKey(id)){
			throw new IllegalArgumentException("contact ID does not exist");
		}
		
		contacts.remove(id); // Delete the contact object
    }

	/**
	 * Updates the first name of a contact by ID.
	 *
	 * @param id contact ID
	 * @param first new first name
	 * @throws IllegalArgumentException if the ID is invalid or not found
	 */
    public void updateFirstName(String id, String first) {
        find(id).setFirstName(first);
	}
	
	/**
	 * Updates the last name of a contact by ID.
	 *
	 * @param id contact ID
	 * @param last new last name
	 * @throws IllegalArgumentException if the ID is invalid or not found
	 */
    public void updateLastName(String id, String last) {
        find(id).setLastName(last);
    }
	
	/**
	 * Updates the phone number of a contact by ID.
	 *
	 * @param id contact ID
	 * @param phone new phone number
	 * @throws IllegalArgumentException if the ID is invalid or not found
	 */
    public void updatePhone(String id, String phone) {
        find(id).setPhone(phone);
    }
	
	/**
	 * Updates the address of a contact by ID.
	 *
	 * @param id contact ID
	 * @param address new address
	 * @throws IllegalArgumentException if the ID is invalid or not found
	 */
    public void updateAddress(String id, String address) {
        find(id).setAddress(address);
    }

    /**
	* Returns a contact by ID.
	*
	* @param id contact ID
	* @return contact
	*/
    public Contact getContactById(String id) {
        return find(id);
    }

    // ---- private helpers ----

    private Contact find(String id) {
		Contact c = contacts.get(id); // Get contact value from the HashMap
		
		// Throw exception if contact ID does not exist
		if (c == null) {
			throw new IllegalArgumentException("Contact ID not found");
		}
		
		return c; // Return the found contact object
    }
}
