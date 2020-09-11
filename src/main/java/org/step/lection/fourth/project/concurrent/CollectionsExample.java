package org.step.lection.fourth.project.concurrent;

import java.util.*;
import java.util.concurrent.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CollectionsExample {

    public static void main(String[] args) {
        Random random = new Random();

        // ArrayList not concurrent safety collection

        List<Integer> collect = Stream.generate(() -> random.nextInt(10000))
                .limit(1000)
                .collect(Collectors.toCollection(CopyOnWriteArrayList::new));

        Iterator<Integer> iterator = collect.iterator();

        while (iterator.hasNext()) {
            collect.remove(iterator.next());
        }
    }

    // not usable
    private static void concurrentNotRightRealizationCollections() {
        Collection<Object> collection = new ArrayList<>();

        Collection<Object> objects2 = Collections.synchronizedCollection(collection);

        List<Object> objects1 = Collections.synchronizedList(List.of());

        Map<Object, Object> objectObjectMap = Collections.synchronizedMap(Map.of());

        Set<Object> objects = Collections.synchronizedSet(Set.of());
    }

    private static void concurrentCollections() {
        Comparator<Integer> integerComparator = (firstInteger, secondInteger) -> firstInteger - secondInteger;

        List<Object> copyOnWriteArrayList = new CopyOnWriteArrayList<>();

        Set<Object> copyOnWriteArraySet = new CopyOnWriteArraySet<>();

        // Map<K, V>
        ConcurrentMap<Integer, Object> concurrentMap = new ConcurrentHashMap<>();

        ConcurrentNavigableMap<Integer, Object> concurrentNavigableMap = new ConcurrentSkipListMap<>(
                // comparator
                integerComparator
        );

        Set<Integer> concurrentSkipListSet = new ConcurrentSkipListSet<>(integerComparator);

        Queue<Object> linkedQueue = new ConcurrentLinkedQueue<>();

        Deque<Object> linkedDeque = new ConcurrentLinkedDeque<>();
    }
}
