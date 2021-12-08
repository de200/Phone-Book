package com.phonebook;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;


public class Phonebook {

    public static final String PHONEBOOK_FILE = "C:\\phonebook\\directory.txt";
    public static final String NAMES_TO_FIND_FILE = "C:\\phonebook\\find.txt";


    private final List<Person> phonebookList =  new ArrayList<>();;
    private final Map<String, String> phonebookMap = new Hashtable<>(2,0.75F   );

    public Phonebook() {
        createListFromFile();
    }

    public List<Person> getPhonebookList() {
        return phonebookList;
    }

    public Map<String, String> getPhonebookMap() {
        return phonebookMap;
    }

    private void createListFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(PHONEBOOK_FILE))) {

            String currentLine;
            while ((currentLine = reader.readLine()) != null) {
                String[] arr = currentLine.split(" ", 2);
                phonebookList.add(new Person(arr[0], arr[1]));

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void createMapFromList() {
        for(Person person: phonebookList) {
            phonebookMap.put(person.getName(), person.getName());
        }
    }

    public static List<String> getNamesToFindFromFile() {
        List<String> namesToFind = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(NAMES_TO_FIND_FILE))) {
            String currentLine;
            while ((currentLine = reader.readLine()) != null) {
                namesToFind.add(currentLine);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return namesToFind;
    }

}
