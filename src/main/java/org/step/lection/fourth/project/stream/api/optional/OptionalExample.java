package org.step.lection.fourth.project.stream.api.optional;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class OptionalExample {

    private static final Map<Integer, Person> PERSON_MAP = new HashMap<>();

    static {
       PERSON_MAP.put(1, new Person("First"));
       PERSON_MAP.put(2, new Person("Second"));
       PERSON_MAP.put(3, null);
       PERSON_MAP.put(4, new Person("Fourth"));
    }

    public static Person getPersonByIdFromDatabase(int id) {
        return PERSON_MAP.get(id);
    }

    public static class Person {
        private String name;

        public Person(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Person person = (Person) o;
            return Objects.equals(name, person.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name);
        }
    }
}
