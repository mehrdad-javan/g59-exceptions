package se.lexicon.contact_app.data;

import se.lexicon.contact_app.exception.ContactStorageException;
import se.lexicon.contact_app.exception.DuplicateContactException;
import se.lexicon.contact_app.model.Contact;

import java.util.List;

public interface ContactDAO {
    List<Contact> findAll() throws ContactStorageException;

    void save(Contact contact) throws ContactStorageException, DuplicateContactException;

    Contact findByName(String name) throws ContactStorageException;
}
