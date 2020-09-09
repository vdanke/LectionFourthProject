package org.step.lection.fourth.project.enums;

import org.step.lection.fourth.project.stream.api.Person;

import java.util.Arrays;
import java.util.stream.Stream;

public class ExampleEnum {

    public static void main(String[] args) {
        Person person = Person.builder(
                "first", Role.USER
        );

        System.out.println(person.getRole());

        // name - строковое представление enum
        System.out.println(person.getRole().name());

        // description - через конструктор
//        System.out.println(person.getRole().getDescription());

        if (person.getRole().equals(Role.USER)) {
            System.out.println("I'am user");
        }

        switch (person.getRole()) {
            case USER:
                break;
            case ADMIN:
                break;
            default:
                Role[] values = Role.values();
                throw new IllegalArgumentException("This is enum, only valid " + Arrays.toString(values));
        }

        Role value = Role.valueOf("USER");

        validateRole("UsER");

        validateRole("HULK");

        boolean isRoleValid = validateRoleWithBoolean("User");

        if (isRoleValid) {
            System.out.println("Role is valid");
        } else {
            throw new RuntimeException();
        }
    }

//    get().fetch().status(400 -> {
//
//    })

    private static void validateRole(final String role) {
        Stream.of(Role.values())
                .filter(r -> r.name().equalsIgnoreCase(role))
                .findAny()
                .orElseThrow(RuntimeException::new);
    }

    private static boolean validateRoleWithBoolean(final String role) {
        return Stream.of(Role.values())
                .anyMatch(r -> r.name().equalsIgnoreCase(role));
    }
}
