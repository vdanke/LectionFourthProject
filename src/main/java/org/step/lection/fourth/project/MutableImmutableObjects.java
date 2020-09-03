package org.step.lection.fourth.project;

public class MutableImmutableObjects {

    public static void main(String[] args) {
        MutableImmutableObjects mIObj = new MutableImmutableObjects();

        StringBuilder strBuilder = new StringBuilder("Surprise");
        String testStr = "New test";

        testStr = mIObj.fourth(testStr);

        mIObj.first(strBuilder);

        System.out.println(testStr);
        System.out.println(strBuilder);
    }

    void first(StringBuilder str) {
        str.append(" add string");
        second(str);
    }

    void second(StringBuilder str) {
        str.append(" new added string");
        third(str);
    }

    void third(StringBuilder str) {
        str.append(" bla bla");
    }

    String fourth(String str) {
        return str.concat(" concat");
    }
}
