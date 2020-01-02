package com.designpatterns.Observer;

public enum WeatherType {

    SUNNY, RAINY, WINDY, COLD;

    @Override
    public String toString() {
        return this.name().toUpperCase();
    }
}
