package com.designpatterns.Observer.example1;

import java.util.ArrayList;

public class Weather {

    private WeatherType weather;
    public ArrayList<IWeatherObserver> observerList;

    public Weather(WeatherType weatherType) {
        observerList=new ArrayList<>();
        weather=weatherType;
    }

    public void addObserver(IWeatherObserver observer)
    {
        observerList.add(observer);
        System.out.println(observer.getName()+" added successfully");

    }

    public void removeObserver(IWeatherObserver observer)
    {
        observerList.remove(observer);
        System.out.println(observer.getName()+" removed successfully");
    }

    public WeatherType getWeather() {
        return weather;
    }

    public void setWeather(WeatherType weather) {
        this.weather = weather;
    }

    public void notifyObservers()
     {
         for(IWeatherObserver observer:observerList)
         {
             observer.update(this);
         }
     }

     public void changeWeather(WeatherType weather)
     {
         this.setWeather(weather);
         this.notifyObservers();
     }

}
