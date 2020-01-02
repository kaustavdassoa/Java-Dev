package com.designpatterns.Observer;

public class Main {

    public static void main(String[] args) {
        Weather weather=new Weather(WeatherType.SUNNY);
        WeatherApp mobileApp=new WeatherApp("Mobile weather app");
        weather.addObserver(mobileApp);
        weather.addObserver(new WeatherApp("Desktop weather app"));
        weather.addObserver(new WeatherWatch("iWatch"));
        weather.addObserver(new WeatherWatch("googleWatch"));
        System.out.println("\n\n");
        weather.changeWeather(WeatherType.RAINY);
        System.out.println("\n\n");
        weather.removeObserver(mobileApp);
        weather.changeWeather(WeatherType.COLD);
    }
}
