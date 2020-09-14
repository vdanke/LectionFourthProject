package org.step.lection.fourth.project.annotations;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class DeprecatedClassProcessor {

    public Set<? extends Class<?>> changeOldClassProcessor(List<String> classList) {
        Package[] packages = Package.getPackages();

        final String dot = ".";

        return classList
                .stream()
                .map(str -> {
                    for (Package aPackage : packages) {
                        try {
                            return Class.forName(aPackage.getName() + dot + str);
                        } catch (ClassNotFoundException e) {
                            continue;
                        }
                    }
                    return null;
                })
                .filter(cls -> cls != null && cls.isAnnotationPresent(MarkDeprecatedClass.class))
                .peek(cls -> System.out.println(cls.getSimpleName()))
                .map(cls -> cls.getAnnotation(MarkDeprecatedClass.class).showNewClass())
                .collect(Collectors.toSet());
    }
}
