package org.step.lection.fourth.project.stream.api;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class CreationStreams {

    public static void main(String[] args) {

    }

    public void creationStreams(List<Person> people, Character[] chars) throws IOException {
//        people.forEach(System.out::println);
//        List<Character> collect = intStream.mapToObj(i -> (char) i).collect(Collectors.toList());
        AtomicInteger atomicInteger = new AtomicInteger(0);

        Stream<Person> personStream = people.stream();
        Stream<Integer> integerStream = Stream.of(1, 2, 3, 4, 5);
        Stream<Character> characterStream = Arrays.stream(chars);
        Stream<String> lines = Files.lines(Paths.get("test.txt"));
        IntStream intStream = "First is very good".chars();
        Stream<Person> parallelPersonStream = people.parallelStream();
        Stream<Integer> iterate = Stream.iterate(0, n -> n + 1);
        Stream<Person> generate = Stream.generate(
                () -> new Person(atomicInteger.incrementAndGet(), UUID.randomUUID().toString().substring(1, 15))
        );
        Stream<Object> objectStream = Stream.builder()
                .add(1)
                .add(new Person())
                .add("abc")
                .build();

        // File working
        Reader in = new FileReader("test.txt");
        BufferedReader bf = new BufferedReader(in);
        Stream<String> bufferedReaderStream = bf.lines();

    }
}
