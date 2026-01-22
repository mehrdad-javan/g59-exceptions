package se.lexicon.contact_app.exception;

public class ExceptionHandler {

    public static void handle(Exception e) {
        if (e instanceof IllegalArgumentException) {
            System.err.println("Validation Error: " + e.getMessage());
        } else if (e instanceof DuplicateContactException) {
            System.err.println("Business Error: " + e.getMessage());
        } else if (e instanceof ContactStorageException) {
            System.err.println("System Error: " + e.getMessage());
            if (e.getCause() != null) {
                System.err.println("Cause: " + e.getCause().getMessage());
            }
        } else {
            System.err.println("Unexpected error occurred: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
