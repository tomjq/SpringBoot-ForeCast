package com.weather.demo.service;

import java.util.List;

import com.weather.demo.domain.City;

public interface CityService {
	List<City> cityList() throws Exception;

}
