package com.phonebook;


import java.util.Collections;
import java.util.List;
import java.util.Map;

public class PhonebookHandling {


    public int countLinearSearchHits(List<Person> phonebook, List<String> namesToFind) {
        int counter = 0;
        for (String name : namesToFind) {
            for (Person person : phonebook)
                if (name.equals(person.getName())) {
                    counter++;
                    break;
                }
        }
        return counter;
    }

    public void bubbleSort(List<Person> phonebook) {
        boolean unsorted = true;

        while (unsorted) {
            unsorted = false;
            for (int i = 0; i < phonebook.size() - 1; i++) {
                if (phonebook.get(i).compareTo(phonebook.get(i + 1)) > 0) {
                    Person temp = phonebook.get(i + 1);
                    phonebook.set(i + 1, phonebook.get(i));
                    phonebook.set(i, temp);
                    unsorted = true;
                }
            }
        }
    }

    private int jumpSearch(List<Person> phonebook, String name) {
        int length = phonebook.size();
        int step = (int) Math.floor(Math.sqrt(length));
        int prev = 0;
        while (phonebook.get(Math.min(step, length) - 1).getName().compareTo(name) < 0) {
            prev = step;
            step += (int) (Math.sqrt(length));
            if (prev >= length)
                return -1;
        }
        while (phonebook.get(prev).getName().compareTo(name) < 0) {
            prev++;
            if (prev == Math.min(step, length))
                return -1;

        }
        if (phonebook.get(prev).getName().equals(name))
            return prev;
        return -1;
    }

    public int countJumpSearchHits(List<Person> phonebook, List<String> namesToFind) {
        int counter = 0;
        for (String name : namesToFind) {
            if (jumpSearch(phonebook, name) != -1)
                counter++;

        }
        return counter;
    }


    private int partition(List<Person> phonebook, int begin, int end) {
        Person pivot = phonebook.get(end);
        int i = begin - 1;

        for (int j = begin; j < end; j++) {
            if (phonebook.get(j).compareTo(pivot) <= 0) {
                i++;
                Collections.swap(phonebook, i, j);
            }
        }
        Collections.swap(phonebook, i + 1, end);
        return i + 1;
    }

    public void quickSort(List<Person> phonebook, int begin, int end) {

        if (begin < end) {
            int partitionIndex = partition(phonebook, begin, end);

            quickSort(phonebook, begin, partitionIndex - 1);
            quickSort(phonebook, partitionIndex + 1, end);
        }
    }

    private boolean binarySearch(List<Person> phonebook, String name) {
        int start = 0;
        int end = phonebook.size() - 1;

        while (start <= end) {

            int middle = start + (end - start) / 2;

            if (name.compareTo(phonebook.get(middle).getName()) > 0) {
                start = middle + 1;
            } else if (name.compareTo(phonebook.get(middle).getName()) < 0) {
                end = middle - 1;
            } else if (name.compareTo(phonebook.get(middle).getName()) == 0) {
                return true;
            }
        }
        return false;
    }

    public int countBinarySearch(List<Person> phonebook, List<String> namesToFind) {
        int counter = 0;
        for (String name : namesToFind) {

            if (binarySearch(phonebook, name)) {
                counter++;
            }
        }
        return counter;
    }

    public int countNamesFoundInMap(Map<String, String> phonebookMap, List<String> namesToFind) {
        int counter = 0;
        for (String name : namesToFind) {
            if (phonebookMap.containsKey(name)) {
                counter++;
            }
        }
        return counter;
    }
}


