package com.egadgets.demo3.service;

import com.egadgets.demo3.dao.CityDAO;
import com.egadgets.demo3.model.City;

import javax.sql.DataSource;
import java.util.ArrayList;

public class CityService {
    private DataSource dataSource;
    public static CityService INSTANCE;

    private CityService() {

    }

    public static CityService getInstance() {
        if (INSTANCE == null) {
            synchronized (CityService.class) {
                if (INSTANCE == null) {
                    INSTANCE = new CityService();
                }
            }
        }
        return INSTANCE;
    }

    public ArrayList<City> getCities() {
        return CityDAO.getCities(dataSource);
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
}
