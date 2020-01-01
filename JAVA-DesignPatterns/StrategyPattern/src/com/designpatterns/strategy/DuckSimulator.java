package com.designpatterns.strategy;

/*
The Strategy Pattern defines a family of algorithms,
encapsulates each one, and makes them interchangeable. Strategy
lets the algorithm vary independently from clients that use it.
 */
public class DuckSimulator {

    public static void main(String[] args) {
        RubberDuck rubberDuck=new RubberDuck(new FakeFly(),new QuiteQuack());
        rubberDuck.performFly();
        rubberDuck.performQuack();
        rubberDuck.setFlyBehavior(new BionicsFly());
        rubberDuck.performFly();
    }
}
