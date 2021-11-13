package com.designpatterns.decorator.example3;

public class TargetObject implements  SomeBehaviour{
    @Override
    public void somefunction() {
        System.out.println("Executing target object behaviour");
    }
}
