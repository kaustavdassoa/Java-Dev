package com.designpatterns.decorator.example3;

public class AfterWrapperObject implements SomeBehaviour{

    SomeBehaviour targetObject;
    public AfterWrapperObject(SomeBehaviour targetObject) {
        this.targetObject=targetObject;
    }

    @Override
    public void somefunction() {
        this.targetObject.somefunction();
        System.out.println("after target object execcuted");
    }
}
