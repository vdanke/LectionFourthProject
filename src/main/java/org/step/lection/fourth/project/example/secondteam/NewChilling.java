package org.step.lection.fourth.project.example.secondteam;

import org.step.lection.fourth.project.example.MyCoolAPI;

public class NewChilling implements MyCoolAPI {

    @Override
    public void chill() {
        System.out.println("New chill");
    }

    @Override
    public String relax() {
        return "New relaxing";
    }
}
