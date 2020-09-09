package org.step.lection.fourth.project.stream.api.optional;

import org.step.lection.fourth.project.stream.api.Person;

import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.OptionalDouble;
import java.util.OptionalInt;
import java.util.stream.Collectors;

public class IntStreamExample {

    private static void showExample(List<Person> personList) {
        OptionalInt max = personList
                .stream()
                .mapToInt(Person::getId)
                .max();

        OptionalInt min = personList
                .stream()
                .mapToInt(Person::getId)
                .min();

        OptionalDouble average = personList
                .stream()
                .mapToInt(Person::getId)
                .average();

        int sum = personList
                .stream()
                .mapToInt(Person::getId)
                .sum();

        IntSummaryStatistics intSummaryStatistics = personList
                .stream()
                .mapToInt(Person::getId)
                .summaryStatistics();

        double average1 = intSummaryStatistics.getAverage();
        long count = intSummaryStatistics.getCount();
        int max1 = intSummaryStatistics.getMax();
        int min1 = intSummaryStatistics.getMin();
        long sum1 = intSummaryStatistics.getSum();

        List<Person> people = personList
                .stream() // создаем стрим
                .mapToInt(Person::getId) // делаем стрим айдишников
                .peek(System.out::println) // смотрим какие айдишники проходят
                .mapToObj(IntStreamExample::findById) // возвращаем по айдишника Person'ов
                .collect(Collectors.toList()); // собираем обратно в лист

        List<Person> collect = personList
                .stream()
                .peek(System.out::println)
                .parallel()
                .unordered() // декларируем выполнение параллельного стрима беспорядочно
                .filter(p -> p.getId() % 2 == 0)
                .collect(Collectors.toList());

        List<Person> list = personList
                .stream()
                .distinct() // собирает только уникальные элементы в стриме equals and hashCode
                .collect(Collectors.toList());
    }

    private static Person findById(Integer id) {
        return new Person(id, id.toString());
    }

}
