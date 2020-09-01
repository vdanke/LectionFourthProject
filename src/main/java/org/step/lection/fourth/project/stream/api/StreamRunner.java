package org.step.lection.fourth.project.stream.api;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StreamRunner {

    public static void main(String[] args) {
        List<String> stringList = new ArrayList<>(Arrays.asList("fir", "second", "thirid"));

//        for (String str : stringList) {
//            if (str.length() > 4) {
//                stringList.remove(str);
//            }
//        }

        String nothing_found = stringList.stream()
                .filter(str -> str.length() > 4)
                .findAny()
                .orElse("Nothing found");

        System.out.println(nothing_found);

        stringList.forEach(System.out::println);
    }
}
