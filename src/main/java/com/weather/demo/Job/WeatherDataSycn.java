package com.weather.demo.Job;

import java.util.List;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.weather.demo.domain.City;
import com.weather.demo.service.CityService;
import com.weather.demo.service.ForeCastService;

public class WeatherDataSycn extends QuartzJobBean{
	@Autowired
	CityService Cs;
	@Autowired
	ForeCastService Fcs;
	@Override
	protected void executeInternal(JobExecutionContext jobExcutionContext) throws JobExecutionException {
		System.out.println("天气同步数据开始");
		List<City>cityList=null;
		try { 
			cityList = Cs.cityList();
      } catch (Exception e) {
	     System.out.println(e.getMessage());
	    } //遍历城市ID获取天气
		for (City city : cityList) { 
			String cityId = city.getCityId(); 
			System.out.println("天气数据同步Job,cityId:" + cityId);
			Fcs.syncDataByCityId(cityId); 
			} 
		System.out.println("天气数据同步Job,End!");
		}
		
	}

