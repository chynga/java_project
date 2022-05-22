package com.egadgets.demo3.model;

import java.util.HashMap;

public class City {
    private int id;
    private String cityName;
    private int countryId;
    HashMap<Integer, String> hash = new HashMap<>();

    public City(int id, String cityName, int countryId) {
        this.id = id;
        this.cityName = cityName;
        this.countryId = countryId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public int getCountryId() {
        return countryId;
    }

    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }

    @Override
    public String toString() {
        return "City{" +
                "id=" + id +
                ", cityName='" + cityName + '\'' +
                ", countryId=" + countryId +
                '}';
    }
}
