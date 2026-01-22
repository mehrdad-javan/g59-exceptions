package se.lexicon.contact_app.exception;

public class DuplicateContactException extends Exception {
    public DuplicateContactException(String message) {
        super(message);
    }
}
