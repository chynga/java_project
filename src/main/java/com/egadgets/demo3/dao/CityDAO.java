package com.egadgets.demo3.dao;

import com.egadgets.demo3.model.City;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class CityDAO {
    public static ArrayList<City> getCities(DataSource dataSource) {
        ArrayList<City> cities = new ArrayList<>();
        Connection connection = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            connection = dataSource.getConnection();
            String query = "Select * from city";
            stmt = connection.createStatement();
            rs = stmt.executeQuery(query);
            while(rs.next()) {
                int id = rs.getInt("id");
                String cityName = rs.getString("city_name");
                int countryId = rs.getInt("country_id");
                cities.add(new City(id, cityName, countryId));
            }
            connection.close();
            stmt.close();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return cities;
    }
}
