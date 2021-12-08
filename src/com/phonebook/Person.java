package com.phonebook;

import java.util.Objects;

public class Person implements Comparable<Person> {

    private String phone;
    private String name;

    public Person(String phone, String name) {
        this.phone = phone;
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Person)) return false;
        Person person = (Person) o;
        return Objects.equals(getPhone(), person.getPhone()) && Objects.equals(getName(), person.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPhone(), getName());
    }

    @Override
    public int compareTo(Person p) {
        return this.name.compareTo(p.getName());
    }

    @Override
    public String toString() {
        return "Person{" +
                "phone='" + phone + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
