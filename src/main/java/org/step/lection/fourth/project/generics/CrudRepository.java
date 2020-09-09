package org.step.lection.fourth.project.generics;

import org.step.lection.fourth.project.stream.api.Person;

import java.util.List;
import java.util.Optional;

public interface CrudRepository<ID, E extends Person> {

    Optional<E> findById(ID id);

    List<? extends Person> findAll();
}
