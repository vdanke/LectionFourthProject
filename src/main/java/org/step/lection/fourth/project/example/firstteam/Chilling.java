package org.step.lection.fourth.project.example.firstteam;

import org.step.lection.fourth.project.example.MyCoolAPI;

public class Chilling implements MyCoolAPI {

    @Override
    public void chill() {
        System.out.println("Yeaah Chill");
    }

    @Override
    public String relax() {
        return "Relaxing";
    }
}
