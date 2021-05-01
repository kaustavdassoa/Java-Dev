package com.designpatterns.factoryMethod.example1;

import java.util.ArrayList;
import java.util.Random;

public class RamdomPetFactory implements AnimalFactory{
    @Override
    public Animal createAnimal() {
        ArrayList<Animal> animals=new ArrayList<>();
        animals.add(new Cat());
        animals.add(new Dog());

        return animals.get(new Random().nextInt(2));

    }
}
