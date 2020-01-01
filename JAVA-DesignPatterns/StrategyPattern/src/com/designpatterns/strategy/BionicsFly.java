package com.designpatterns.strategy;

public class BionicsFly implements FlyBehavior {
    @Override
    public void fly() {
        System.out.println("Mechanically flying");
    }
}
