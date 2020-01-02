package com.designpatterns.Observer;

public class WeatherWatch extends IWeatherObserver {

    public WeatherWatch(String displayName) {
        this.setName(displayName);
    }

    @Override
    public void update(Weather weather) {
        System.out.println("["+this.getName()+"]"+" Current Weather is : "+weather.getWeather());
    }
}
