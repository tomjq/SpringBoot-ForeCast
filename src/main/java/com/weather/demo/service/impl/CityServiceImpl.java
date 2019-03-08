package com.weather.demo.service.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.List;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import com.weather.demo.domain.City;
import com.weather.demo.domain.CityList;
import com.weather.demo.service.CityService;
import com.weather.demo.util.XmlBuilder;
@Service
public class CityServiceImpl implements CityService {

	@Override
	public List<City> cityList() throws Exception{
		//读取Xml文件
		Resource rosource=new ClassPathResource("cityList.xml");
		BufferedReader br=new BufferedReader(new InputStreamReader(rosource.getInputStream(),"utf-8"));
		StringBuffer buffer=new StringBuffer();
		String line="";
		while((line=br.readLine())!=null){
			buffer.append(line);
		}
		br.close();
		//把XML转化为对像
		CityList cityList=(CityList)XmlBuilder.xmlStrToObject(CityList.class,buffer.toString());
		return cityList.getCityList();
	}

}
