package com.weather.demo.service.impl;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.weather.demo.domain.ForeCast;
import com.weather.demo.domain.WeatherResponse;
import com.weather.demo.service.ForeCastService;
@Service
public class ForeCastServiceImpl implements ForeCastService{
	private  static final String Url="http://wthrcdn.etouch.cn/weather_mini?";
	@Autowired
	private RestTemplate restTemplate;
	@Autowired
	private StringRedisTemplate stringRedisTemplate;
	@Override
	public WeatherResponse getDataByCityId(String cityId) {
		String URL=Url+"citykey="+cityId;
		return this.doGetWeather(URL);
	}
	private WeatherResponse doGetWeather(String URL) {
		ObjectMapper mapper = new ObjectMapper(); 
		WeatherResponse resp = null; 
		String strBody = null;
		ValueOperations<String,String> vps=stringRedisTemplate.opsForValue();
		//判断缓存中是否有天气预报
		if(stringRedisTemplate.hasKey(URL)){
			System.err.println("redis has data");
			strBody=vps.get(URL);
		}else{	
		//没有就调用接口
			System.out.println("redis no data");
		ResponseEntity<String> respString = restTemplate.getForEntity(URL,String.class); 
		if (respString.getStatusCodeValue() == 200){ 
			strBody = respString.getBody(); 
			} 
		   //写入缓存中
		   vps.set(URL, strBody, 1800L, TimeUnit.SECONDS);
		}
		try { 
			resp = mapper.readValue(strBody,WeatherResponse.class);
			} 
		catch (IOException e) {
			e.printStackTrace();
			}
		
		return resp;
	}		
	@Override
	public WeatherResponse getDataByCityName(String cityName) {
		String uri=Url+"city="+cityName;
		return this.doGetWeather(uri);
	}
	@Override
	public void syncDataByCityId(String cityId) {
		String uri=Url+"cityId="+cityId;
		this.SaveWeather(uri);
		
	}
	private void SaveWeather(String uri) {
		 String key = uri; String strBody = null; 
		 ValueOperations<String, String> ops = stringRedisTemplate.opsForValue(); 
		 //调用服务接口来获取
		 ResponseEntity<String> respString = restTemplate.getForEntity(uri,String.class); 
		 //将接口返回的Json字符串转换成对象 
		 if (respString.getStatusCodeValue() == 200) {
			 strBody = respString.getBody(); 
			 } 
		 //数据写入缓存 
		 ops.set(uri,strBody,1800L, TimeUnit.SECONDS); 
		 }

		 
	}



