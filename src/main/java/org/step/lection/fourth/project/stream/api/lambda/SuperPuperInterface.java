package org.step.lection.fourth.project.stream.api.lambda;

import org.step.lection.fourth.project.stream.api.Person;

// 1. Проверяет что данный интерфейс является функциональным
// 2. Данный интерфейс декларируется в Javadoc как функциональный
@FunctionalInterface
public interface SuperPuperInterface<T extends Person> {

    String transformToSuperString(T t);

    default void showThisPerson(T t) {
        System.out.println(t.toString());
    }

    // default ...

    static <T> void showPersonId(T t) {
        if (t.getClass().isAssignableFrom(Person.class)) {
            System.out.println(((Person) t).getId());
        }
    }

    // static ...
}
