package org.step.lection.fourth.project.stream.api.lambda;

import org.step.lection.fourth.project.stream.api.Person;

public class SuperPuperPerson implements SuperPuperInterface<Person> {

    @Override
    public String transformToSuperString(Person s) {
        return s.getUsername();
    }
}
