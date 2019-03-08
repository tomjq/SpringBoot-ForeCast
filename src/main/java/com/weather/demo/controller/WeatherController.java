

package com.weather.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.weather.demo.domain.WeatherResponse;
import com.weather.demo.service.ForeCastService;

@RestController
@RequestMapping("/weather")
public class WeatherController {
	@Autowired
	ForeCastService Fcs;
	@GetMapping("/cityId/{cityId}")
	public WeatherResponse getWeatherCityId(@PathVariable("cityId")String cityId){
		return Fcs.getDataByCityId(cityId);
	}
	@GetMapping("/cityName/{cityName}")
	public WeatherResponse getWeatherCityName(@PathVariable("cityName")String cityName){
		return Fcs.getDataByCityName(cityName);
		
	}		
	

}

