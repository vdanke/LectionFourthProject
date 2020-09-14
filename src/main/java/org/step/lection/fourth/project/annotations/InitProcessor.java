package org.step.lection.fourth.project.annotations;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

public class InitProcessor {

    public void processInit(List<NewClass> classes) {
        Package[] packages = Package.getPackages();

        final String dot = ".";

        classes
                .stream()
                .map(str -> {
                    for (Package aPackage : packages) {
                        try {
                            return Class.forName(aPackage.getName() + dot + str.getClass().getSimpleName());
                        } catch (ClassNotFoundException e) {
                            continue;
                        }
                    }
                    return null;
                })
                .filter(Objects::nonNull)
                .filter(cls -> Stream.of(cls.getDeclaredFields()).anyMatch(field -> field.isAnnotationPresent(InitAnnotation.class)))
                .forEach(cls -> {
                    Field[] declaredFields = cls.getDeclaredFields();

                    Stream.of(declaredFields)
                            .forEach(field -> {
                                if (field.getType().isAssignableFrom(SuperClass.class)) {
                                    field.setAccessible(true);
                                    try {
                                        field.set(classes.get(0), new SuperClass());
                                    } catch (IllegalAccessException e) {
                                        e.printStackTrace();
                                    }
                                }
                            });
                });
    }
}
