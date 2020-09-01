package org.step.lection.fourth.project.stream.api;

import java.util.Optional;
import java.util.UUID;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class FunctionalInterfaceExample {

    // Валидирует
    Predicate<String> stringPredicate = new Predicate<String>() {
        @Override
        public boolean test(String s) {
            final int i = 5;

            return s.length() > i;
        }
    };

    // Снабжает
    Supplier<String> stringSupplier = new Supplier<String>() {
        @Override
        public String get() {
            return UUID.randomUUID().toString().substring(5, 15);
        }
    };

    // Потребляет
    Consumer<String> stringConsumer = new Consumer<String>() {
        @Override
        public void accept(String s) {
            System.out.println(s);
        }
    };

    // Трансформирует
    Function<String, Integer> stringIntegerFunction = new Function<String, Integer>() {
        @Override
        public Integer apply(String s) {
            return s.length();
        }
    };

    Function<String, Integer> lambdaStringIntegerFunction = (str) -> str.length();

    public static void main(String[] args) {
        FunctionalInterfaceExample example = new FunctionalInterfaceExample();

        Integer aaskldkjqwndk = example.stringIntegerFunction.apply("aaskldkjqwndk");

        boolean isStringValid = example.stringPredicate.test("abc");

        System.out.println(isStringValid);

        String s = Optional.of("abc") // Создание Optional
                .filter(example.stringPredicate) // Передача Predicate в фильтр
                .orElse("Nothing"); // Если ничего не найдено - возвращаем Nothing

        Optional.of("abc")
                .map(example.stringIntegerFunction)
                .ifPresent(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) {
                        System.out.println(integer);
                    }
                });

        Optional<String> testString = Optional.of("abc");

        String s1 = testString.orElseGet(new Supplier<String>() {
            @Override
            public String get() {
                return "Nothing";
            }
        });

        System.out.println(s);

        Integer abc = example.stringIntegerFunction.apply("abc");

        System.out.println(abc);
    }
}
