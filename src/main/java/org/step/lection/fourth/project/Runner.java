package org.step.lection.fourth.project;

import org.step.lection.fourth.project.example.*;
import org.step.lection.fourth.project.example.firstteam.Chilling;
import org.step.lection.fourth.project.example.secondteam.NewChilling;

public class Runner {

    /*
    PRINCIPLES SOLID

    S - single responsibility principle
    O - open-closed principle
    L - Liskof principle
    I - Interface segregation principle
    D - dependency inversion principle

    Set<Transport>

    |_|_|_|_|_|_|
    1-2
      3-4
        5-6

     */
    public static void main(String[] args) {
//        Transport first = new ElectricCar(1.1, "first");
//        Transport second = new FuelCar(1.1, "first");
//        Transport third = new ElectricCar(11.1, "first");
//
//        Set<Transport> transportSet = new HashSet<>();
//
//        transportSet.add(first);
//        transportSet.add(third);
//
//        Runner.showEngine(second);
//
//        boolean isEqual = first == second;
//
//        boolean isEqualSecondVariant = first.equals(third);
//
//        System.out.println(isEqual);
//        System.out.println(isEqualSecondVariant);

       MyCoolAPI myCoolAPI = new NewChilling();

       Runner.makeItEasy(myCoolAPI);
    }

    private static void showEngine(Transport transport) {
        System.out.println(transport.getVersion());
    }

    private static void makeItEasy(MyCoolAPI myCoolAPI) {
        myCoolAPI.chill();

        System.out.println(myCoolAPI.relax());
    }
}
