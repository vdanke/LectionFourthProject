package org.step.lection.fourth.project.stream.api.lambda;

import org.step.lection.fourth.project.stream.api.Person;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.function.Predicate;

public class LambdaExample {

//    static {
//        System.out.println("Static block");
//    }
//
//    {
//        System.out.println("Usual block");
//    }

//    public LambdaExample() {
//        System.out.println("Constructor");
//    }

    public static void main(String[] args) {
        SuperPuperInterface<Person> superPuperInterface = new SuperPuperInterface<>() {
            @Override
            public String transformToSuperString(Person person) {
                return person.getUsername();
            }
        };

        SuperPuperInterface<Person> personSuper = pers -> pers.getUsername();

        Person prs = new Person(1, "First");

        personSuper.showThisPerson(prs);

        personSuper.transformToSuperString(prs);

        SuperPuperInterface.showPersonId(prs);

//        List<Person> personList = LambdaExample.findAll("adsad");
        Comparator<Person> personComparator = new Comparator<Person>() {
            @Override
            public int compare(Person person, Person t1) {
                return Integer.compare(person.getId(), t1.getId());
            }
        };
        Comparator<Person> lambdaPersonComparator =
                (first, second) -> {
                    return Integer.compare(first.getId(), second.getId());
                };

        Predicate<Person> personPredicate = new Predicate<Person>() {
            @Override
            public boolean test(Person person) {
                return person.getUsername() != null;
            }
        };

        //@Nullable как модификатор входящего аргумента (любая аннотация которая может быть перед входящим аргументом)
        Predicate<Person> ultraPredicate = (final Person person) -> {
            if (person == null) {
                return false;
            }
            if (person.getId() == 0) {
                return false;
            }
            return person.getUsername() != null;
        };

        // Многопоточность
        Runnable runnable = () -> {
        };

        Callable<String> callable = () -> "abc";

        Comparator<Person> evolutionLambda = Comparator.comparingInt(Person::getId);

        Person[] people = new Person[]{
                new Person(2, "second"),
                new Person(3, "third"),
                new Person(1, "first")
        };

        Arrays.sort(people, evolutionLambda);

        for (Person p : people) {
            System.out.println(p.getId());
        }
    }

//    private static <T extends Person> List<T> findAll(String username) {
//        return new ArrayList<>();
//    }
}
