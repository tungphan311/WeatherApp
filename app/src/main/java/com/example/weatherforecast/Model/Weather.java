package com.example.weatherforecast.Model;

public class Weather {
    public String hour;
    public String icon;
    public String mintemp;
    public String maxtemp;
    public String iconphrase;
    public String hum;
    public String wind;

    public Weather(String hour, String icon, String mintemp, String maxtemp, String iconphrase, String hum, String wind) {
        this.hour = hour;
        this.icon = icon;
        this.mintemp = mintemp;
        this.maxtemp = maxtemp;
        this.iconphrase = iconphrase;
        this.hum = hum;
        this.wind = wind;
    }
}
