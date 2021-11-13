package com.designpatterns.decorator.example3;

public class TestApplication {

    public static void main(String[] args) {

        TargetObject targetObject=new TargetObject();
        SomeBehaviour aroundWrapperObject=new AroundWrapperObject(targetObject);
        SomeBehaviour beforeWrapperObject= new BeforeWrapperObject(targetObject);
        SomeBehaviour afterWrapperObject=new AfterWrapperObject(targetObject);
        System.out.println("+++++++++++AROUND DEMO+++++++++++++++");
        aroundWrapperObject.somefunction();
        System.out.println("+++++++++++BEFORE DEMO+++++++++++++++");
        beforeWrapperObject.somefunction();
        System.out.println("+++++++++++AFTER DEMO+++++++++++++++");
        afterWrapperObject.somefunction();

    }
}
