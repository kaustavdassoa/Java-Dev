package com.designpatterns.Observer.example1;

public abstract class IWeatherObserver implements Comparable<IWeatherObserver> {
    private String name;
    public abstract void update(Weather weather);

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int compareTo(IWeatherObserver iWeatherObserver) {
        int compareVal=0;
        return this.getName().compareToIgnoreCase(iWeatherObserver.getName());
    }
}
