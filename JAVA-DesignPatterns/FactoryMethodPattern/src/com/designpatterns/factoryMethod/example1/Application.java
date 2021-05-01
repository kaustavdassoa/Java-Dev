package com.designpatterns.factoryMethod.example1;

public class Application {

    public static void main(String[] args) {
        System.out.println("Animal Created = "+new RamdaomAnimalFactory().createAnimal().getAnimalName());
        System.out.println("Animal Created = "+new RamdaomAnimalFactory().createAnimal().getAnimalName());
        System.out.println("Animal Created = "+new RamdaomAnimalFactory().createAnimal().getAnimalName());
        System.out.println("Animal Created = "+new RamdaomAnimalFactory().createAnimal().getAnimalName());


        System.out.println("*********************************************************************");
        System.out.println("Animal Created = "+new RamdomPetFactory().createAnimal().getAnimalName());
        System.out.println("Animal Created = "+new RamdomPetFactory().createAnimal().getAnimalName());
        System.out.println("Animal Created = "+new RamdomPetFactory().createAnimal().getAnimalName());
        System.out.println("Animal Created = "+new RamdomPetFactory().createAnimal().getAnimalName());
    }
}
