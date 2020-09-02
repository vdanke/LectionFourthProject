package org.step.lection.fourth.project.stream.api.lambda;

import org.step.lection.fourth.project.stream.api.Person;

import java.util.Comparator;

public class PersonComparator implements Comparator<Person> {

    @Override
    public int compare(Person first, Person second) {
        return Integer.compare(first.getId(), second.getId());
    }
}
