package contact;

import java.util.ArrayList;
import java.util.List;

/**
 * Provides business logic for managing Contact objects.
 *
 * This service enforces validation, uniqueness, and consistent
 * behavior for adding, updating, and deleting contacts.
 */
public class ContactService {

    private final List<Contact> contacts = new ArrayList<>();

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
        return new ArrayList<>(contacts);
    }

	/**
	 * Adds a new contact to the service.
	 *
	 * @param contact contact to add
	 * @throws IllegalArgumentException if the contact is null or the ID already exists
	 */
    public void addContact(Contact contact) {
        if (contact == null) {
            throw new IllegalArgumentException("contact cannot be null");
        }
        validateUniqueContactID(contact.getContactID());
        contacts.add(contact);
    }

	/**
	 * Deletes an existing contact by ID.
	 *
	 * @param id contact ID to delete
	 * @throws IllegalArgumentException if the ID is null, empty, or not found
	 */
    public void deleteContact(String id) {
        validateId(id);
        Contact c = find(id);
        contacts.remove(c);
    }

	/**
	 * Updates the first name of a contact by ID.
	 *
	 * @param id contact ID
	 * @param first new first name
	 * @throws IllegalArgumentException if the ID is invalid or not found
	 */
    public void updateFirstName(String id, String first) {
        validateId(id);
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
        validateId(id);
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
        validateId(id);
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
        validateId(id);
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

    private void validateId(String id) {
        if (id == null || id.isBlank()) {
            throw new IllegalArgumentException("Contact ID cannot be null or empty");
        }
    }

    private void validateUniqueContactID(String id) {
        validateId(id);
        for (Contact c : contacts) {
            if (c.getContactID().equals(id)) {
                throw new IllegalArgumentException("Contact ID already exists");
            }
        }
    }

    private Contact find(String id) {
        validateId(id);
        for (Contact c : contacts) {
            if (c.getContactID().equals(id)) {
                return c;
            }
        }
        throw new IllegalArgumentException("Contact ID not found");
    }
}
