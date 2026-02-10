package contact;

import java.util.ArrayList;
import java.util.List;

public class ContactService {

    // Arraylist of contacts
    private final List<Contact> contacts = new ArrayList<>();
    
    public ContactService() {
    	
    }

    // Add contact
    public void addContact(Contact contact) {
        // duplicate-ID check
    	validateUniqueContactID(contact.getContactID());
        contacts.add(contact);
    }

    // Delete contact
    public void deleteContact(String id) {
        contacts.removeIf(c -> c.getContactID().equals(id));
    }
    
    // Verify a contact ID is unique
    private void validateUniqueContactID(String id) {
        for (Contact c : contacts) {
            if (c.getContactID().equals(id)) {
                throw new IllegalArgumentException("Contact ID already exists");
            }
        }
    }

    // Update contact
    private Contact find(String id) {
        for (Contact c : contacts) {
            if (c.getContactID().equals(id)) {
                return c;
            }
        }
        throw new IllegalArgumentException("Contact ID not found");
    }

    public void updateFirstName(String id, String first) { find(id).setFirstName(first); }
    public void updateLastName (String id, String last ) { find(id).setLastName(last);  }
    public void updatePhone    (String id, String phone) { find(id).setPhone(phone);   }
    public void updateAddress  (String id, String addr ) { find(id).setAddress(addr);  }
}
