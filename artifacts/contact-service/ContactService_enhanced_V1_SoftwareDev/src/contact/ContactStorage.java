package contact;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 * Handles persistence of contacts to and from a text file.
 *
 * Contacts are stored using a simple delimiter-based format
 * with one contact per line.
 */
public class ContactStorage {

    private final Path filePath;

    /**
     * Create a storage handler pointing to a specific file path.
	 *
     * @param filePath Path to the storage file
	 * @throws IllegalArgumentException if filePath is null
     */
    public ContactStorage(Path filePath) {
        if (filePath == null) {
            throw new IllegalArgumentException("filePath cannot be null");
        }
        this.filePath = filePath;
    }

    /**
     * Load contacts from disk. If the file does not exist, returns an empty list.
	 *
     * @return list of contacts loaded from storage
	 * @throws IllegalStateException if an I/O error occurs while reading the file
     */
    public List<Contact> load() {
        if (!Files.exists(filePath)) {
            return new ArrayList<>();
        }

        try {
            List<String> lines = Files.readAllLines(filePath, StandardCharsets.UTF_8);
            List<Contact> contacts = new ArrayList<>();

            for (String line : lines) {
                if (line == null || line.isBlank()) {
                    continue;
                }
                // Expected: id|first|last|phone|address
                String[] parts = line.split("\\|", -1);
                if (parts.length != 5) {
                    continue; // skip malformed lines
                }

                try {
                    contacts.add(new Contact(parts[0], parts[1], parts[2], parts[3], parts[4]));
                } catch (IllegalArgumentException ex) {
                    // Skip invalid records rather than crashing load
                }
            }

            return contacts;
        } catch (IOException ex) {
            throw new IllegalStateException("Failed to load contacts from file: " + filePath, ex);
        }
    }

    /**
     * Save contacts to disk (overwrites file).
	 *
     * @param contacts list of contacts to save
	 * @throws IllegalStateException if an I/O error occurs while writing the file
     */
    public void save(List<Contact> contacts) {
        if (contacts == null) {
            throw new IllegalArgumentException("contacts cannot be null");
        }

        List<String> lines = new ArrayList<>();
        for (Contact c : contacts) {
            // Use '|' delimiter. (Assumes address/names don't include '|'. Good enough for milestone.)
            lines.add(c.getContactID() + "|" + c.getFirstName() + "|" + c.getLastName() + "|" + c.getPhone() + "|" + c.getAddress());
        }

        try {
            Files.write(filePath, lines, StandardCharsets.UTF_8);
        } catch (IOException ex) {
            throw new IllegalStateException("Failed to save contacts to file: " + filePath, ex);
        }
    }
}
