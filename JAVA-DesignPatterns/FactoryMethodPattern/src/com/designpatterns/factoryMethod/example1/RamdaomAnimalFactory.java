package com.designpatterns.factoryMethod.example1;

import java.util.ArrayList;
import java.util.Random;

public class RamdaomAnimalFactory implements AnimalFactory{
    @Override
    public Animal createAnimal() {
        ArrayList<Animal> animals=new ArrayList<>();
        animals.add(new Cat());
        animals.add(new Dog());
        animals.add(new Cow());

        return animals.get(new Random().nextInt(3));

    }
}
