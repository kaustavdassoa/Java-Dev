package com.designpatterns.Observer.example1;

public class Main {

    public static void main(String[] args) {

        Weather weather=new Weather(WeatherType.SUNNY);
        System.out.println("Weather Set to SUNNY");
        WeatherApp mobileApp=new WeatherApp("Mobile weather app");
        weather.addObserver(mobileApp);
        weather.addObserver(new WeatherApp("Desktop weather app"));
        weather.addObserver(new WeatherWatch("iWatch"));
        weather.addObserver(new WeatherWatch("googleWatch"));
        System.out.println("Weather Change to RAINY");
        weather.changeWeather(WeatherType.RAINY);
        System.out.println("Weather Change to COLD");
        weather.removeObserver(mobileApp);
        weather.changeWeather(WeatherType.COLD);
    }
}
