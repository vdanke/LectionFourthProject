package org.step.lection.fourth.project.stream.api;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class IntermediateMethods {

    public static void main(String[] args) {
        // final collection List.of
//        List<Integer> integers = List.of(1, 2, 3, 4);

        List<Person> personList = Arrays.asList(
                new Person(2, "second", Arrays.asList("bla bla")),
                new Person(3, "third", Arrays.asList("bla bla")),
                new Person(1, "first", Arrays.asList("bla bla")),
                new Person(1, "new first", Arrays.asList("bla bla")),
                new Person(4, "bla bla", Arrays.asList("bla bla"))
        );

        IntermediateMethods.intermediateMethodsWork(personList);
    }

    public static void intermediateMethodsWork(List<Person> personList) {
//        personList
//                .stream()
//                .filter()
//                .map()
//                .distinct()
//                .collect(Collectors.toList());
//
//        Stream<Person> stream = personList.stream();
//
//        System.out.println(stream.count());

//        System.out.println(countStream(stream));
//
//        stream.forEach(System.out::println);

        personList
                .stream()
                .filter(prs -> prs.getId() > 1)
                .forEach(System.out::println);

        long count = personList
                .stream()
                .skip(2)
                .count();

        System.out.println(count);

        personList
                .stream()
                .distinct()
                .forEach(System.out::println);

        personList
                .stream()
                .map(prs -> prs.getUsername())
                .forEach(System.out::println);

        Stream.of(new Person())
                .filter(prs -> prs.getUsername() != null)
                .peek(prs -> {
                    System.out.println(
                            String.format(
                                "The user with ID %d and username %s is present",
                                prs.getId(),
                                prs.getUsername()
                            )
                    );
                })
                .findAny()
                .ifPresent(prs -> System.out.println("BOOOOM"));

        long personGenerateCount = Stream.generate(Person::new).limit(1000).count();

        System.out.println(personGenerateCount);

//        Comparator.comparingInt(Person::getId);
//        (first, second) -> {
//            return first.getId() - second.getId();
//        }
        personList
                .stream()
                .sorted()
                .forEach(System.out::println);

        IntStream intStream = personList
                .stream()
                .mapToInt(Person::getId);

//        Stream.Builder<Object> builder = Stream.builder();
//
//        personList
//                .stream()
//                .map(Person::getMovies)
//                .map(Collection::stream)
//                .map(stringStream -> )


        Stream<String> stringStream = personList
                .stream()
                .flatMap(prs -> prs.getMovies().stream());
    }

    private static long countStream(Stream<Person> personStream) {
        return personStream.count();
    }
}
