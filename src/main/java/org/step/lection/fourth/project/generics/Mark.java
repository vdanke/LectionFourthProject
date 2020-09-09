package org.step.lection.fourth.project.generics;

public class Mark<T extends Number> {

    T mark;


//    public static <T> T t; wrong variant

    public static <T> void randomMark(T t) {
        System.out.println(t);
    }

    public static Number rrr(Number number) {
        System.out.println(number);

        return number;
    }

    public void showMarkWithGeneric(Mark<T> mark) {
        System.out.println(mark.toString());
    }

    public void showMarkWithWildcard(Mark<?> mark) {
        System.out.println(mark.toString());
    }

    public T getMark() {
        return mark;
    }

    public void setMark(T mark) {
        this.mark = mark;
    }

    @Override
    public String toString() {
        return "Mark{" +
                "mark=" + mark +
                '}';
    }

    public static void main(String[] args) {
        Mark.randomMark(1L);
        Mark.rrr(1);

        Mark<Integer> mark = new Mark<>();
        Mark<Long> longMark = new Mark<>();

        mark.setMark(1);
        longMark.setMark(1L); // (long) 1

        mark.showMarkWithGeneric(mark);

        mark.showMarkWithWildcard(longMark);
    }
}
