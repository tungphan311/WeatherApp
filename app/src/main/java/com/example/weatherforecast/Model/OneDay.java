package com.example.weatherforecast.Model;

public class OneDay {
    public String Day;
    public String Status;
    public String Icon;
    public String Temp;
    public String Wind;
    public String Pressure;
    public String Humidity;

    public OneDay(String day, String status, String icon, String temp, String wind, String pressure, String humidity) {
        Day = day;
        Status = status;
        Icon = icon;
        Temp = temp;
        Wind = wind;
        Pressure = pressure;
        Humidity = humidity;
    }

    public String getDay() {
        return Day;
    }

    public void setDay(String day) {
        Day = day;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public String getIcon() {
        return Icon;
    }

    public void setIcon(String icon) {
        Icon = icon;
    }

    public String getTemp() {
        return Temp;
    }

    public void setTemp(String temp) {
        Temp = temp;
    }

    public String getWind() {
        return Wind;
    }

    public void setWind(String wind) {
        Wind = wind;
    }

    public String getPressure() {
        return Pressure;
    }

    public void setPressure(String pressure) {
        Pressure = pressure;
    }

    public String getHumidity() {
        return Humidity;
    }

    public void setHumidity(String humidity) {
        Humidity = humidity;
    }
}
