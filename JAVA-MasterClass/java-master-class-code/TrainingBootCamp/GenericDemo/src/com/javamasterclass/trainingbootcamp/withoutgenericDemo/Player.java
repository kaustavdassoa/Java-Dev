package com.javamasterclass.trainingbootcamp.withoutgenericDemo;

public class Player {

    private String name;

    public Player(String name) {
        this.name = name;
    }

//    @Override
//    public String toString() {
//        return "Player{" +
//                "name='" + name + '\'' +
//                '}';
//    }

    @Override
    public String toString() {
        return name;
    }
}
