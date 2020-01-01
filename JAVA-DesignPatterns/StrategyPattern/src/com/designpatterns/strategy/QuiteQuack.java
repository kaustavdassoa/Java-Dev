package com.designpatterns.strategy;

public class QuiteQuack implements QuackBehavior {
    @Override
    public void quack() {
        System.out.println("Cant Quake");
    }
}
