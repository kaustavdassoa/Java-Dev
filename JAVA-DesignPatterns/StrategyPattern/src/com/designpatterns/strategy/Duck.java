package com.designpatterns.strategy;

//Parent Class
public class Duck {

    private FlyBehavior flyBehavior;
    private QuackBehavior quackBehavior;


    public Duck(FlyBehavior flyBehavior, QuackBehavior quackBehavior) {
        this.flyBehavior = flyBehavior;
        this.quackBehavior = quackBehavior;
    }

    public void setFlyBehavior(FlyBehavior flyBehavior) {
        this.flyBehavior = flyBehavior;
    }

    public void setQuackBehavior(QuackBehavior quackBehavior) {
        this.quackBehavior = quackBehavior;
    }

    public void performFly()
    {
        this.flyBehavior.fly();
    }//performFly

    public void performQuack()
    {
        this.quackBehavior.quack();
    }//performFly
}
