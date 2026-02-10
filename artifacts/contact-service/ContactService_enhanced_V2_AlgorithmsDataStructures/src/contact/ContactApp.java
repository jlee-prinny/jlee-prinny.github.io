package contact;

import java.util.List;
import java.util.Scanner;
import java.nio.file.Path;

/**
 * Command-line interface for interacting with the ContactService.
 */
public class ContactApp {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ContactService service = new ContactService();
		ContactStorage storage = new ContactStorage(Path.of("contacts.txt"));
		
		// Load on startup
		service.loadAll(storage.load());
        boolean running = true;

        while (running) {
            System.out.println("Contact Service Application");
            System.out.println("1. Add Contact");
            System.out.println("2. View Contacts");
            System.out.println("3. Update Contact information");
            System.out.println("4. Delete Contact");
            System.out.println("5. Exit");
            System.out.print("Select an option: ");

            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    handleAdd(scanner, service, storage);
                    break;
                case "2":
                    handleView(service);
                    break;
				case "3":
					handleUpdate(scanner, service, storage);
					break;
				case "4":
					handleDelete(scanner, service, storage);
					break;
                case "5":
                    running = false;
                    System.out.println("Goodbye.");
                    break;
                default:
                    System.out.println("Invalid choice");
            }

            System.out.println(); // blank line between loops
        }

        scanner.close();
    }

    private static void handleAdd(Scanner scanner, ContactService service, ContactStorage storage) {
        try {
            System.out.print("Contact ID (max 10): ");
            String id = scanner.nextLine().trim();

            System.out.print("First name (max 10): ");
            String first = scanner.nextLine().trim();

            System.out.print("Last name (max 10): ");
            String last = scanner.nextLine().trim();

            System.out.print("Phone (10 digits): ");
            String phone = scanner.nextLine().trim();

            System.out.print("Street Address (max 30): ");
            String address = scanner.nextLine().trim();

            Contact contact = new Contact(id, first, last, phone, address);
            service.addContact(contact);
			storage.save(service.getAllContacts());

            System.out.println("Contact added.");
        } catch (IllegalArgumentException ex) {
            System.out.println("Could not add contact: " + ex.getMessage());
        }
    }

    private static void handleView(ContactService service) {
        List<Contact> contacts = service.getAllContacts();

        if (contacts.isEmpty()) {
            System.out.println("No contacts found.");
            return;
        }

        System.out.println("Contacts:");
        for (Contact c : contacts) {
            System.out.println("- ID: " + c.getContactID()
                    + " | " + c.getFirstName() + " " + c.getLastName()
                    + " | Phone: " + c.getPhone()
                    + " | Street Address: " + c.getAddress());
        }
    }
	
	private static void handleUpdate(Scanner scanner, ContactService service, ContactStorage storage) {
		try {
			System.out.print("Enter Contact ID to update: ");
			String id = scanner.nextLine().trim();

			Contact c = service.getContactById(id);

			System.out.println("Update this contact:");
			System.out.println("- ID: " + c.getContactID()
					+ " | " + c.getFirstName() + " " + c.getLastName()
					+ " | Phone: " + c.getPhone()
					+ " | Address: " + c.getAddress()
					+ "\n");
					
			System.out.println("Select the attribute to update: ");
            System.out.println("1. First Name");
            System.out.println("2. Last Name");
            System.out.println("3. Phone Number");
            System.out.println("4. Street Address");
            System.out.println("5. Cancel");
            System.out.print("Select an option: ");
			
			String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
					System.out.print("Enter the new first name (max 10): ");
					choice = scanner.nextLine().trim();
					service.updateFirstName(id, choice);
					storage.save(service.getAllContacts());
					System.out.print("Contact first name updated.");
                    break;
                case "2":
					System.out.print("Enter the new last name (max 10): ");
					choice = scanner.nextLine().trim();
					service.updateLastName(id, choice);
					storage.save(service.getAllContacts());
					System.out.print("Contact last name updated.");
                    break;
				case "3":
					System.out.print("Enter the new phone number (10 digits): ");
					choice = scanner.nextLine().trim();
					service.updatePhone(id, choice);
					storage.save(service.getAllContacts());
					System.out.print("Contact phone number updated.");
					break;
				case "4":
					System.out.print("Enter the new street address (max 30): ");
					choice = scanner.nextLine().trim();
					service.updateAddress(id, choice);
					storage.save(service.getAllContacts());
					System.out.print("Contact street address updated.");
					break;
                case "5":
                    System.out.println("Update canceled.");
                    break;
                default:
                    System.out.println("Invalid choice");
            }

            System.out.println(); // blank line between loops
			
		} catch (IllegalArgumentException ex) {
			System.out.println("Update failed: " + ex.getMessage());
		}
	}
	
	private static void handleDelete(Scanner scanner, ContactService service, ContactStorage storage) {
		try {
			System.out.print("Enter Contact ID to delete: ");
			String id = scanner.nextLine().trim();

			Contact c = service.getContactById(id);

			System.out.println("Delete this contact?");
			System.out.println("- ID: " + c.getContactID()
					+ " | " + c.getFirstName() + " " + c.getLastName()
					+ " | Phone: " + c.getPhone()
					+ " | Address: " + c.getAddress());

			while (true) {
				System.out.print("Are you sure? (y/n): ");
				String confirm = scanner.nextLine().trim().toLowerCase();

				if (confirm.equals("y")) {
					service.deleteContact(id);
					storage.save(service.getAllContacts());
					System.out.println("Contact deleted.");
					return;
				} else if (confirm.equals("n")) {
					System.out.println("Delete canceled.");
					return;
				} else {
					System.out.println("Please enter 'y' or 'n'.");
				}
			}
		} catch (IllegalArgumentException ex) {
			System.out.println("Delete failed: " + ex.getMessage());
		}
	}
}
