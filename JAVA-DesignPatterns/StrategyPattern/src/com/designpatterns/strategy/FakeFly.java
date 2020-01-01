package com.designpatterns.strategy;

public class FakeFly implements FlyBehavior {
    @Override
    public void fly() {
        System.out.println("Cant Fly");
    }
}
