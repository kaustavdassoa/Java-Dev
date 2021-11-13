package com.designpatterns.decorator.example3;

public class AroundWrapperObject implements SomeBehaviour{

    private SomeBehaviour targetObject;

    public AroundWrapperObject(SomeBehaviour targetObject) {
        this.targetObject=targetObject;
    }

    @Override
    public void somefunction() {
        System.out.println("Before target Object executed ");
        this.targetObject.somefunction();
        System.out.println("After target Object executed");
    }
}
