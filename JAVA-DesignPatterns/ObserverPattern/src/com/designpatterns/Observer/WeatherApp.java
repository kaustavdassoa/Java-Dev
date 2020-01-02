package com.designpatterns.Observer;

public class WeatherApp extends IWeatherObserver{

    public WeatherApp(String displayName) {
        this.setName(displayName);

    }

    @Override
    public void update(Weather weather) {
        System.out.println("["+this.getName()+"]"+" Current Weather is : "+weather.getWeather());
    }



}
