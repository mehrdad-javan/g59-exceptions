package se.lexicon.contact_app.data;

import se.lexicon.contact_app.exception.ContactStorageException;
import se.lexicon.contact_app.exception.DuplicateContactException;
import se.lexicon.contact_app.model.Contact;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

public class FileContactDAOImpl implements ContactDAO {

    private final Path filePath;

    public FileContactDAOImpl(Path filePath) {
        this.filePath = filePath;
        // Ensure the directory exists
        try {
            if (filePath.getParent() != null) {
                Files.createDirectories(filePath.getParent());
            }
            if (!Files.exists(filePath)) {
                Files.createFile(filePath);
            }
        } catch (IOException e) {
            System.err.println("Warning: Could not initialize storage file: " + e.getMessage());
        }
    }

    @Override
    public List<Contact> findAll() throws ContactStorageException {
        List<Contact> contacts = new ArrayList<>();
        try (BufferedReader reader = Files.newBufferedReader(filePath)) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.isBlank()) continue;
                try {
                    String[] parts = line.split(";");
                    if (parts.length != 2) {
                        throw new ArrayIndexOutOfBoundsException("Invalid line format: " + line);
                    }
                    contacts.add(new Contact(parts[0], parts[1]));
                } catch (Exception e) {
                    // We catch parsing errors specifically so one bad line doesn't crash the whole load
                    System.err.println("Skipping corrupted line: " + line + " (Reason: " + e.getMessage() + ")");
                }
            }
        } catch (IOException e) {
            // Wrapping low-level IO error into our business-level storage exception
            throw new ContactStorageException("Failed to load contacts from storage", e);
        }
        return contacts;
    }

    @Override
    public void save(Contact contact) throws ContactStorageException, DuplicateContactException {
        // First check for duplicates
        List<Contact> existing = findAll();
        if (existing.contains(contact)) {
            throw new DuplicateContactException("Contact with name '" + contact.getName() + "' already exists.");
        }

        // Append to file
        try (BufferedWriter writer = Files.newBufferedWriter(filePath, StandardOpenOption.APPEND)) {
            writer.write(contact.toString());
            writer.newLine();
        } catch (IOException e) {
            throw new ContactStorageException("Failed to save contact to storage", e);
        }
    }

    @Override
    public Contact findByName(String name) throws ContactStorageException {
        return findAll().stream()
                .filter(c -> c.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElse(null);
    }
}
