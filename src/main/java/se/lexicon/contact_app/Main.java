package se.lexicon.contact_app;

import se.lexicon.contact_app.controller.ContactController;
import se.lexicon.contact_app.data.ContactDAO;
import se.lexicon.contact_app.data.FileContactDAOImpl;
import se.lexicon.contact_app.view.ContactView;

import java.nio.file.Paths;

public class Main {

    void main() {
        // Initialize Model (DAO)
        ContactDAO contactDAO = new FileContactDAOImpl(Paths.get("contacts.txt"));

        // Initialize View
        ContactView contactView = new ContactView();

        // Initialize Controller
        ContactController controller = new ContactController(contactDAO, contactView);

        // Start the application
        controller.run();
    }
}
