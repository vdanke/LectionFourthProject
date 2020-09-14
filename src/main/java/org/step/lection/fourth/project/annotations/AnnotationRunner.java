package org.step.lection.fourth.project.annotations;

import java.util.List;
import java.util.Set;

public class AnnotationRunner {

    public static void main(String[] args) {
//        DeprecatedClassProcessor deprecatedClassProcessor = new DeprecatedClassProcessor();
//
//        Set<? extends Class<?>> oldClass = deprecatedClassProcessor.changeOldClassProcessor(List.of("OldClass"));
//
//        oldClass.forEach(cls -> System.out.println(cls.getSimpleName()));

        InitProcessor initProcessor = new InitProcessor();

        NewClass newClass = new NewClass();

        System.out.println(newClass.superClass);

        initProcessor.processInit(List.of(newClass));

        System.out.println(newClass.superClass.field);
    }
}
