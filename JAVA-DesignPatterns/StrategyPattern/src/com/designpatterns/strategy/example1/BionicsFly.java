package com.designpatterns.strategy.example1;

public class BionicsFly implements FlyBehavior {
    @Override
    public void fly() {
        System.out.println("Mechanically flying");
    }
}
