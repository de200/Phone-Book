package com.phonebook;

import java.util.Collections;
import java.util.List;
import java.util.Map;

public class Main {

    private static String timeConverter(long start, long end) {
        long difference = end - start;

        int milliseconds = (int) (difference % 1000);
        int seconds = (int) (difference / 1000);
        int minutes = seconds / 60;
        return String.format("%d min. %d sec. %d ms.", minutes, seconds, milliseconds);

    }

    public static void main(String[] args) {
        Phonebook p1 = new Phonebook();
        Phonebook p2 = new Phonebook();
        Phonebook p3 = new Phonebook();


        PhonebookHandling handler = new PhonebookHandling();

        List<String> namesToFind = Phonebook.getNamesToFindFromFile();

        List<Person> phonebook1 = p1.getPhonebookList();
        List<Person> phonebook2 = p2.getPhonebookList();
        List<Person> phonebook3 = p3.getPhonebookList();


        long startLinearSearching = System.currentTimeMillis();
        System.out.println("Start searching (linear search)...");

        int counter = handler.countLinearSearchHits(phonebook1, namesToFind);
        long endLinearSearching = System.currentTimeMillis();
        int entries = namesToFind.size();

        System.out.println("Found " + counter + " / " + entries + " entries. Time taken: " + timeConverter(startLinearSearching, endLinearSearching));


        long startBubbleSorting = System.currentTimeMillis();
        System.out.println("\nStart searching (bubble sort + jump search)..");
//        handler.bubbleSort(phonebook2);
        Collections.sort(phonebook2);
        long endBubbleSorting = System.currentTimeMillis();

        long startJumpSearching = System.currentTimeMillis();
        int jumpCounter = handler.countJumpSearchHits(phonebook2, namesToFind);
        long endJumpSearching = System.currentTimeMillis();

        System.out.println("Found " + jumpCounter + " / " + entries + " entries. Time taken: " + timeConverter(startBubbleSorting, endJumpSearching));
        System.out.println("Sorting time: " + timeConverter(startBubbleSorting, endBubbleSorting));
        System.out.println("Searching time: " + timeConverter(startJumpSearching, endJumpSearching));


        long startQuickSorting = System.currentTimeMillis();
        System.out.println("\nStart searching (quick sort + binary search)...");
        handler.quickSort(phonebook3, 0, phonebook3.size() - 1);
        long endQuickSorting = System.currentTimeMillis();
        long startBinarySearching = System.currentTimeMillis();
        int binaryCounter = handler.countBinarySearch(phonebook3, namesToFind);
        long endBinarySearching = System.currentTimeMillis();

        System.out.println("Found " + binaryCounter + " / " + entries + ". Time taken: " + timeConverter(startQuickSorting, endBinarySearching));
        System.out.println("Sorting time: " + timeConverter(startQuickSorting, endQuickSorting));
        System.out.println("Searching time: " + timeConverter(startBinarySearching, endBinarySearching));


        long startCreatingMap = System.currentTimeMillis();
        p3.createMapFromList();
        long endCreatingMap = System.currentTimeMillis();
        Map<String, String> phonebook4 = p3.getPhonebookMap();
        System.out.println("\nStart searching (hash table)...");
        int namesCounter = handler.countNamesFoundInMap(phonebook4, namesToFind);
        long endSearchingMap = System.currentTimeMillis();

        System.out.println("Found " + namesCounter + " / " + entries + ". Time taken: " + timeConverter(startCreatingMap, endSearchingMap));
        System.out.println("Creating time: " + timeConverter(startCreatingMap, endCreatingMap));
        System.out.println("Searching time: " + timeConverter(endCreatingMap, endSearchingMap));
    }
}
