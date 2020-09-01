package org.step.lection.fourth.project.stream.api.optional;

import java.util.Optional;
import java.util.function.Supplier;

public class OptionalRunner {

    public static void main(String[] args) {
        Optional<OptionalExample.Person> personByIdFromDatabase = Optional
                .ofNullable(OptionalExample.getPersonByIdFromDatabase(3));

        OptionalExample.Person nothing = personByIdFromDatabase
                .orElse(new OptionalExample.Person("Nothing"));

        Optional<Object> emptyOptional = Optional.empty();

        System.out.println(emptyOptional.isPresent());

        System.out.println(nothing.getName());

        OptionalExample.Person person = Optional.ofNullable(OptionalExample.getPersonByIdFromDatabase(3))
                .orElseThrow(new Supplier<RuntimeException>() {
                    @Override
                    public RuntimeException get() {
                        return new RuntimeException("Person with id 3 not exists");
                    }
                });

        System.out.println(person.getName());

//        if (personByIdFromDatabase.isPresent()) {
//            System.out.println(personByIdFromDatabase.get().getName());
//        } else {
//            System.out.println("This person doesn't exists");
//        }
    }
}
