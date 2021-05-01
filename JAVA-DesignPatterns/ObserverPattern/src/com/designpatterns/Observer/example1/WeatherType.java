package com.designpatterns.Observer.example1;

public enum WeatherType {

    SUNNY, RAINY, WINDY, COLD;

    @Override
    public String toString() {
        return this.name().toUpperCase();
    }
}
