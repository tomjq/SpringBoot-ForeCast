package com.weather.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.weather.demo.domain.ForeCast;
import com.weather.demo.service.CityService;
import com.weather.demo.service.ForeCastService;
@RestController
@RequestMapping("/report")
public class WeatherReportController { 
@Autowired 
private CityService Cs;
@Autowired 
private ForeCastService Fcs;
@GetMapping("/cityId/{cityId}") 
public ModelAndView getReportByCityId(@PathVariable("cityId") String cityId, Model model) throws Exception {
      model.addAttribute("title","天气预报");
      model.addAttribute("cityId",cityId); 
	  model.addAttribute("cityList",Cs.cityList());
	  String cityName=Fcs.getDataByCityId(cityId).getData().getCity();
	  model.addAttribute("cityName", cityName);
	  String wendu=Fcs.getDataByCityId(cityId).getData().getWendu();
	  String ganmao=Fcs.getDataByCityId(cityId).getData().getGanmao();
	  List<ForeCast> forecast=Fcs.getDataByCityId(cityId).getData().getForecast();
	  model.addAttribute("wendu", wendu);
	  model.addAttribute("ganmao", ganmao);
	  model.addAttribute("forecast", forecast);
	  return new ModelAndView("report","reportModel",model);
			}
		}
	


