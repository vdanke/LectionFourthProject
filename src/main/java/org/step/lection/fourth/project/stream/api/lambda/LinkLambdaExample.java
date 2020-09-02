package org.step.lection.fourth.project.stream.api.lambda;

import org.postgresql.Driver;

import java.util.Arrays;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class LinkLambdaExample {

    // super
    Function<Integer, String> integerStringFunction = this::integerToString;

    public static void main(String[] args) {
//        Consumer<String> consumerString = str -> System.out.println(str);
//
//        Consumer<String> ultraSting = System.out::println;
//
//        ultraSting.accept("First baby");

        Integer[] integers = new Integer[]{1, 3, 5, 2, 4, 1111, 123, 444, 321};

        Arrays.sort(integers, Integer::compareTo);

        Stream.of(integers).forEach(System.out::println);

        Supplier<StringBuffer> stringBufferSupplier = StringBuffer::new;

        StringBuffer stringBuffer = stringBufferSupplier.get();
    }

    private String integerToString(Integer integer) {
        return integer.toString();
    }
}
