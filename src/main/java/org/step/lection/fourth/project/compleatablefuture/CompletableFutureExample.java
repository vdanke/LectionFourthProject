package org.step.lection.fourth.project.compleatablefuture;

import org.step.lection.fourth.project.stream.api.Person;

import java.util.*;
import java.util.concurrent.*;
import java.util.stream.Collectors;

public class CompletableFutureExample {

    private static final ExecutorService EXECUTOR_SERVICE = Executors.newFixedThreadPool(10);

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        handle();

        EXECUTOR_SERVICE.shutdown();
    }

    private static List<String> findAllPersonMoviesByPersonId(Integer id) {
        return Arrays.asList(
                "Good", "Bad", "Days"
        );
    }

    private static List<Person> findPersonsByRestRequest() {
        List<Person> personList = new ArrayList<>();

        personList.add(new Person(4, "first rest"));
        personList.add(new Person(5, "second rest"));
        personList.add(new Person(6, "third rest"));

        return personList;
    }

    private static List<Person> findAllFromDatabase() {
        List<Person> personList = new ArrayList<>();

        personList.add(new Person(1, "first"));
        personList.add(new Person(2, "second"));
        personList.add(new Person(3, "third"));

        throw new RuntimeException("Ba bah");

//        return personList;
    }

    private static CompletableFuture<String> findRandomMovieOnConcretePerson(Integer id) {
        return CompletableFuture.supplyAsync(() -> findAllPersonMoviesByPersonId(id))
                .thenApply(strings -> {
                    int size = strings.size();

                    int index = new Random().nextInt(--size);

                    return strings.get(index);
                });
    }

    // completedFuture
    private static void createCompletedFuture() {
        // send the new request to database

        CompletableFuture<List<Person>> firstCompletableFuture = CompletableFuture
                .completedFuture(findAllFromDatabase());

        List<Person> join = firstCompletableFuture.join(); // block

        boolean isEquals = findAllFromDatabase().size() == join.size();

        if (isEquals) {
            System.out.println("Ura");
        } else {
            throw new RuntimeException();
        }
//        assert isEquals;
    }

    // supplyAsync
    private static void createCompletableFutureWithSupplyAsync() {
        Runnable runnable = () -> {
            System.out.println("I am async");
        };
        // ForkJoinPool is running those CompletableFutures
        CompletableFuture<Void> voidCompletableFuture = CompletableFuture.runAsync(runnable);
        CompletableFuture<List<Person>> listCompletableFuture = CompletableFuture.supplyAsync(() -> findAllFromDatabase());

        // Uses our Executor Service
        CompletableFuture<Void> voidCompletableFuture1 = CompletableFuture
                .runAsync(runnable, EXECUTOR_SERVICE);
        CompletableFuture<List<Person>> completableFuture = CompletableFuture
                .supplyAsync(() -> findAllFromDatabase(), EXECUTOR_SERVICE);

        try {
            List<Person> personList = listCompletableFuture.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        List<Person> people = completableFuture.join(); // block

        people
                .stream()
//                .filter()
//                .map()
//                .distinct()
                .findAny()
                .ifPresent(System.out::println);
    }
    // thenAccept
    private static void thenAcceptWithCompletableFuture() {
        CompletableFuture<Void> voidCompletableFuture = CompletableFuture.supplyAsync(() -> findAllFromDatabase(), EXECUTOR_SERVICE) // 1. Find all persons from DB
                .thenAccept(people -> { // 2. Activate callback function on list of persons
                    people
                            .stream()
                            .findAny()
                            .ifPresent(System.out::println);
                });
    }
    // thenApply
    private static void thenApplyWithCompletableFuture() throws ExecutionException, InterruptedException {
//        List<Person> allFromDatabase = findAllFromDatabase(); // 1
//
//        Person person = findAllFromDatabase() // 2
//                .stream()
//                .findFirst()
//                .orElseThrow(RuntimeException::new);
//
//        String username = person.getUsername(); // 3

//        System.out.println(String.format("You are the champion, %s", username)); // 4

        CompletableFuture<Void> stringCompletableFuture = CompletableFuture
                .supplyAsync(() -> findAllFromDatabase(), EXECUTOR_SERVICE) // 1
                .thenApply(personList -> personList.stream().findAny().orElseThrow(RuntimeException::new)) // 2
                .thenApply(Person::getUsername)  // 3
                .thenAccept(System.out::println); // 4

//        Callable<List<Person>> callable = () -> findAllFromDatabase();
//
//        Future<List<Person>> submit = EXECUTOR_SERVICE.submit(callable);
//
//        List<Person> personList1 = submit.get();

//        personList1
//                .stream()
//                .map(person -> {
//                    Callable<List<String>> movies = () -> findAllPersonMoviesByPersonId(person.getId());
//
//                    return EXECUTOR_SERVICE.submit(movies);
//                })
//                .map(listFuture -> {
//                    try {
//                        return listFuture.get();
//                    } catch (InterruptedException | ExecutionException e) {
//                        e.printStackTrace();
//                    }
//                    return null;
//                })
//                .flatMap(strings -> {
//                    assert strings != null;
//                    return strings.stream();
//                })
//                .collect(Collectors.toList());

        CompletableFuture<Void> listCompletableFuture = CompletableFuture
                .supplyAsync(CompletableFutureExample::findAllFromDatabase, EXECUTOR_SERVICE)
                .thenApply(personList -> personList.stream().flatMap(person -> findAllPersonMoviesByPersonId(person.getId()).stream()))
                .thenApply(stringStream -> stringStream.collect(Collectors.toList()))
                .thenAccept(strings -> strings.forEach(System.out::println));
    }

    // thenCompose
    private static void thenComposeWithCompletableFuture() {
//        CompletableFuture<CompletableFuture<String>> completableFutureCompletableFuture = CompletableFuture.supplyAsync(() -> findAllFromDatabase(), EXECUTOR_SERVICE)
//                .thenApply(personList -> personList.get(0))
//                .thenApply(person -> findRandomMovieOnConcretePerson(person.getId()));

        CompletableFuture<String> stringCompletableFuture = CompletableFuture
                .supplyAsync(() -> findAllFromDatabase(), EXECUTOR_SERVICE)
                .thenApply(personList -> personList.get(0))
                .thenCompose(person -> findRandomMovieOnConcretePerson(person.getId()));
    }

    // thenCombine
    private static void thenCombineWithCompletableFuture() {
        CompletableFuture<List<Person>> listCompletableFuture = CompletableFuture.supplyAsync(() -> findAllFromDatabase(), EXECUTOR_SERVICE);
        CompletableFuture<List<Person>> randomMovieOnConcretePerson = CompletableFuture.supplyAsync(() -> findPersonsByRestRequest(), EXECUTOR_SERVICE);

        CompletableFuture<Set<Person>> setCompletableFuture = listCompletableFuture.thenCombine(
                randomMovieOnConcretePerson, (peopleFromDB, peopleFromRestRequest) -> {
                    Set<Person> personSet = new CopyOnWriteArraySet<>();

                    personSet.addAll(peopleFromDB);
                    personSet.addAll(peopleFromRestRequest);

                    return personSet;
                }
        );
        setCompletableFuture.thenAccept(peopleSet -> peopleSet.forEach(System.out::println));
    }

    // applyToEither
    // Кто быстрее
    private static void applyToEither() {
        CompletableFuture<List<Person>> listCompletableFuture = CompletableFuture.supplyAsync(() -> findAllFromDatabase(), EXECUTOR_SERVICE);
        CompletableFuture<List<Person>> personListByRestRequest = CompletableFuture.supplyAsync(() -> findPersonsByRestRequest(), EXECUTOR_SERVICE);

        // first request would be placed here if succeed
        CompletableFuture<List<String>> listCompletableFuture1 = listCompletableFuture
                .applyToEither(personListByRestRequest, personList -> personList.stream().map(Person::getUsername).collect(Collectors.toList()));
    }

    // allOf
    private static void allOf() {
        CompletableFuture<List<Person>> listCompletableFuture = CompletableFuture.supplyAsync(() -> findAllFromDatabase(), EXECUTOR_SERVICE);
        CompletableFuture<List<Person>> randomMovieOnConcretePerson = CompletableFuture.supplyAsync(() -> findPersonsByRestRequest(), EXECUTOR_SERVICE);
        CompletableFuture<List<Person>> third = CompletableFuture.supplyAsync(() -> findAllFromDatabase(), EXECUTOR_SERVICE);
        CompletableFuture<List<Person>> fourth = CompletableFuture.supplyAsync(() -> findPersonsByRestRequest(), EXECUTOR_SERVICE);

        // Is waiting for everybody
        CompletableFuture<Void> voidCompletableFuture = CompletableFuture.allOf(listCompletableFuture, randomMovieOnConcretePerson, third, fourth);

        voidCompletableFuture.join();
    }

    // handle
    private static void handle() {
        CompletableFuture.supplyAsync(() -> findAllFromDatabase(), EXECUTOR_SERVICE)
                .handle((peopleList, throwable) -> {
                    List<Person> personList = new ArrayList<>();

                    if (throwable != null) {
                        sendMailMessage(throwable.getLocalizedMessage());
                    }
                    return throwable != null ? personList : peopleList;
                })
                .thenAccept(System.out::println);
    }

    // exceptionally
    private static void exceptionally() {
        CompletableFuture.supplyAsync(() -> findAllFromDatabase(), EXECUTOR_SERVICE)
                .exceptionally(throwable -> {
                    sendMailMessage(throwable.getLocalizedMessage());
                    return new ArrayList<>();
                })
                .thenAccept(System.out::println);
    }

    // anyOf
    private static void anyOf() {
        CompletableFuture<List<Person>> listCompletableFuture = CompletableFuture.supplyAsync(() -> findAllFromDatabase(), EXECUTOR_SERVICE);
        CompletableFuture<List<Person>> randomMovieOnConcretePerson = CompletableFuture.supplyAsync(() -> findPersonsByRestRequest(), EXECUTOR_SERVICE);
        CompletableFuture<List<Person>> third = CompletableFuture.supplyAsync(() -> findAllFromDatabase(), EXECUTOR_SERVICE);
        CompletableFuture<List<Person>> fourth = CompletableFuture.supplyAsync(() -> findPersonsByRestRequest(), EXECUTOR_SERVICE);

        CompletableFuture<List<Person>> listCompletableFuture1 = CompletableFuture.anyOf(listCompletableFuture, randomMovieOnConcretePerson, third, fourth)
                .thenApply(obj -> (List<Person>) obj);
    }

    // ... array
    private static void exampleFunctionWithManyArguments(String str, Integer i, Person... persons) {
        int length = persons.length;
    }

    private static void sendMailMessage(String localizedMessage) {
        System.out.println(String.format("Sent message is %s", localizedMessage));
    }
}
