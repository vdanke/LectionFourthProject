package org.step.lection.fourth.project.stream.api;

import java.util.*;
import java.util.stream.Collectors;

public class TerminalMethods {

    public static void main(String[] args) {
        List<Person> personList = Arrays.asList(
                new Person(2, "second", Arrays.asList("bla bla")),
                new Person(3, "third", Arrays.asList("bla bla")),
                new Person(1, "first", Arrays.asList("bla bla")),
                new Person(1, "new first", Arrays.asList("bla bla")),
                new Person(4, "bla bla", Arrays.asList("bla bla"))
        );

        terminalMethodsWork(personList);
    }

    public static void terminalMethodsWork(List<Person> personList) {
        Optional<Person> first = personList
                .stream()
                .findFirst();

        Optional<Person> any = personList
                .stream()
                .findAny();

        List<Person> people = personList
                .stream()
                .filter(prs -> prs.getUsername().startsWith("f"))
                .distinct() // unique list
                .collect(Collectors.toList());

        Set<Person> personSet = personList
                .stream()
                .filter(prs -> prs.getUsername().startsWith("f"))
                .collect(Collectors.toSet());

        Map<Integer, String> personIdUsernameMap = personList
                .stream()
                .distinct()
                .collect(Collectors.toMap(
                        Person::getId, // key
                        prs -> prs.getUsername() // value
                ));

        System.out.println(personIdUsernameMap);

        String collect = personList
                .stream()
                .map(Person::getUsername)
                .collect(Collectors.joining("|"));

        long count = personList
                .stream()
                .count();

        boolean isAnyMatched = personList
                .stream()
                .anyMatch(prs -> prs.getId() == 1);

        boolean isAnyNoneMatched = personList
                .stream()
                .noneMatch(prs -> prs.getId() == 55);

        boolean isAllMatched = personList
                .stream()
                .allMatch(prs -> prs.getId() == 1);

        Optional<Person> max = personList
                .stream()
                .max(Comparator.comparingInt(Person::getId));

        Optional<Person> min = personList
                .stream()
                .min(Comparator.comparingInt(Person::getId));

        Person[] toArray = personList
                .stream()
                .toArray(Person[]::new);

        Optional<String> reduce = personList
                .stream()
                .map(Person::getUsername)
                .reduce((str1, str2) -> str1.concat(str2));

        // personList.forEach();

        // maintaining the order
        personList
                .parallelStream()
                .forEachOrdered(System.out::println);

        System.out.println(collect);
    }
}
