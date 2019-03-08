package com.weather.demo.service;

import com.weather.demo.domain.WeatherResponse;

public interface ForeCastService {
	WeatherResponse getDataByCityId(String cityId);
	WeatherResponse getDataByCityName(String cityName);
	void syncDataByCityId(String cityId);
}
