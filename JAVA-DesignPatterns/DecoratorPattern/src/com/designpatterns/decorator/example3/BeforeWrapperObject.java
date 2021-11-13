package com.designpatterns.decorator.example3;

public class BeforeWrapperObject implements SomeBehaviour{

    SomeBehaviour targetObject;

    public BeforeWrapperObject(SomeBehaviour targetObject) {
        this.targetObject=targetObject;
    }

    @Override
    public void somefunction() {
        System.out.println("Before executing target behaviour");
        this.targetObject.somefunction();
    }
}
