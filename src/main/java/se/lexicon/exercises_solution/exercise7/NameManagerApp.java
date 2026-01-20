package se.lexicon.exercises_solution.exercise7;

import java.util.ArrayList;
import java.util.List;

public class NameManagerApp {
    private List<String> names = new ArrayList<>();

    void main() {
        NameManagerApp manager = new NameManagerApp();
        try {
            manager.addName("John");
            manager.addName("Jane");
            manager.addName("John"); // Should throw DuplicateNameException
        } catch (DuplicateNameException e) {
            System.err.println("Error: " + e.getMessage());
        }

        try {
            manager.findName("Alice"); // Should throw NameNotFoundException
        } catch (NameNotFoundException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    public void addName(String name) throws DuplicateNameException {
        if (names.contains(name)) {
            throw new DuplicateNameException("Name '" + name + "' already exists in the list.");
        }
        names.add(name);
        System.out.println("Added name: " + name);
    }

    public String findName(String name) throws NameNotFoundException {
        if (!names.contains(name)) {
            throw new NameNotFoundException("Name '" + name + "' not found in the list.");
        }
        return name;
    }
}
