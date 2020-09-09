package org.step.lection.fourth.project.generics.impl;

import org.step.lection.fourth.project.generics.CrudRepository;
import org.step.lection.fourth.project.stream.api.Person;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class PersonCrudRepository implements CrudRepository<Integer, Person> {

    private static Map<Integer, Person> personMap = new HashMap<>();

    static {
        personMap.put(1, new Person(1, "first"));
    }

    @Override
    public Optional<Person> findById(Integer id) {
        return Optional.ofNullable(
                personMap.get(id)
        );
    }

    @Override
    public List<? extends Person> findAll() {
        return null;
    }
}
