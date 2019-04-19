package com.example.weatherforecast.Model;

public class City {
    public String Name;
    public String Country;
    public String ID;
    public String Lat;
    public String Lon;

    public City(String name, String country, String ID, String lat, String lon) {
        Name = name;
        Country = country;
        this.ID = ID;
        Lat = lat;
        Lon = lon;
    }
}
