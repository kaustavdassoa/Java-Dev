package com.designpatterns.strategy.example1;

public class QuiteQuack implements QuackBehavior {
    @Override
    public void quack() {
        System.out.println("Cant Quake");
    }
}
