package com.designpatterns.strategy.example1;

public class FakeFly implements FlyBehavior {
    @Override
    public void fly() {
        System.out.println("Cant Fly");
    }
}
