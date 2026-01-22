package se.lexicon.contact_app.view;

import se.lexicon.contact_app.model.Contact;

import java.util.List;
import java.util.Scanner;

public class ContactView {
    private final Scanner scanner = new Scanner(System.in);

    public String getUserInput(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }

    public void displayMenu() {
        System.out.println("\n--- Contact Manager ---");
        System.out.println("1. View All Contacts");
        System.out.println("2. Add New Contact");
        System.out.println("3. Search by Name");
        System.out.println("4. Exit");
        System.out.print("Choose an option: ");
    }

    public void displayContacts(List<Contact> contacts) {
        if (contacts.isEmpty()) {
            System.out.println("No contacts found.");
        } else {
            System.out.println("\nContact List:");
            contacts.forEach(System.out::println);
        }
    }

    public void displayContact(Contact contact) {
        if (contact != null) {
            System.out.println("Found: " + contact);
        } else {
            System.out.println("Contact not found.");
        }
    }

    public void displayMessage(String message) {
        System.out.println(message);
    }

    public void displayError(String message) {
        System.err.println("Error: " + message);
    }
}
