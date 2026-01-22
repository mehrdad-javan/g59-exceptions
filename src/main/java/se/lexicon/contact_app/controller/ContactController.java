package se.lexicon.contact_app.controller;

import se.lexicon.contact_app.data.ContactDAO;
import se.lexicon.contact_app.exception.ExceptionHandler;
import se.lexicon.contact_app.model.Contact;
import se.lexicon.contact_app.view.ContactView;

import java.util.List;

public class ContactController {
    private final ContactDAO contactDAO;
    private final ContactView contactView;

    public ContactController(ContactDAO contactDAO, ContactView contactView) {
        this.contactDAO = contactDAO;
        this.contactView = contactView;
    }

    public void run() {
        boolean running = true;
        while (running) {
            try {
                contactView.displayMenu();
                String choice = contactView.getUserInput("");

                switch (choice) {
                    case "1" -> viewAll();
                    case "2" -> addContact();
                    case "3" -> searchByName();
                    case "4" -> {
                        running = false;
                        contactView.displayMessage("Exiting... Goodbye!");
                    }
                    default -> contactView.displayMessage("Invalid option. Try again.");
                }
            } catch (Exception e) {
                // Controller catches exceptions and uses the central handler
                ExceptionHandler.handle(e);
            }
        }
    }

    private void viewAll() throws Exception {
        List<Contact> contacts = contactDAO.findAll();
        contactView.displayContacts(contacts);
    }

    private void addContact() throws Exception {
        String name = contactView.getUserInput("Enter name: ");
        String phone = contactView.getUserInput("Enter phone: ");

        // Model validation might throw IllegalArgumentException
        Contact newContact = new Contact(name, phone);

        // DAO might throw ContactStorageException or DuplicateContactException
        contactDAO.save(newContact);
        contactView.displayMessage("Contact saved successfully!");
    }

    private void searchByName() throws Exception {
        String name = contactView.getUserInput("Enter name to search: ");
        Contact contact = contactDAO.findByName(name);
        contactView.displayContact(contact);
    }
}
