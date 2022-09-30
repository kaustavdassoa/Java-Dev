package com.example.streams;


import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

class Fruit{
    String fruitName;
    Long cal;

    public Fruit(String fruitName, Long cal) {
        this.fruitName = fruitName;
        this.cal = cal;
    }

    public String getFruitName() {
        return fruitName;
    }

    public Long getCal() {
        return cal;
    }
}
public class GroupByExample2 {

    public static void main(String[] args) {
        List<Fruit> fruitBasket= new ArrayList<Fruit>();
        fruitBasket.add(new Fruit("Orange",70L));
        fruitBasket.add(new Fruit("Apple",50L));
        fruitBasket.add(new Fruit("Apple",50L));
        fruitBasket.add(new Fruit("Apple",50L));
        fruitBasket.add(new Fruit("Apple",50L));
        fruitBasket.add(new Fruit("Orange",70L));
        fruitBasket.add(new Fruit("Banana",80L));

        //Map<Fruit,Long>  fruitMap= fruitBasket.stream().collect(Collectors.groupingBy(Function.identity(),Collectors.counting()));

    }

}
