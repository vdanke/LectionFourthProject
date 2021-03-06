package org.step.lection.fourth.project.stream.api;

import org.step.lection.fourth.project.enums.Role;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

// implements comparable
public class Person implements Comparable<Person> {

    private int id;
    private String username;
    private Role role;
    private List<String> movies;

    private Person(String username, Role role) {
        this.username = username;
        this.role = role;
    }

    public Person() {
        this.movies = new ArrayList<>();
    }

    public Person(int id, String username) {
        this.id = id;
        this.username = username;
    }

    public Person(int id, String username, List<String> movies) {
        this.id = id;
        this.username = username;
        this.movies = movies;
    }

    public static Person builder(String username, Role role) {
        return new Person(
                username,
                role
        );
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<String> getMovies() {
        return movies;
    }

    public void setMovies(List<String> movies) {
        this.movies = movies;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return id == person.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", username='" + username + '\'' +
                '}';
    }

    @Override
    public int compareTo(Person person) {
        return this.id - person.getId();
    }
}
