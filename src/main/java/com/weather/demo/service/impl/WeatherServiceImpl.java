package com.weather.demo.service.impl;

import java.net.URI;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.weather.demo.domain.WeatherResponse;
import com.weather.demo.service.ForeCastService;
@Service
public class WeatherServiceImpl implements ForeCastService{
    private static final String url="http://wthrcdn.etouch.cn/weather_mini?";
	@Override
	public WeatherResponse getDataByCityId(String cityId){
		String URL=url+"citykey="+cityId;
		return this.doGetWeather(URL);
		
	}

	private WeatherResponse doGetWeather(String URL){
		String result=null;
		ObjectMapper mapper=new ObjectMapper();
		WeatherResponse Wr=null;
		HttpClient httpClient=HttpClients.createDefault();
        try {
			URIBuilder builder=new URIBuilder(URL);
			URI uri=builder.build();
			HttpGet httpGet=new HttpGet(uri);
			HttpResponse response=httpClient.execute(httpGet);
			if(response.getStatusLine().getStatusCode()==HttpStatus.SC_OK){
			result= EntityUtils.toString(response.getEntity(),"utf-8");
			Wr=mapper.readValue(result,WeatherResponse.class);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
      
        
        return Wr;
	}

	@Override
	public WeatherResponse getDataByCityName(String cityName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void syncDataByCityId(String cityId) {
		// TODO Auto-generated method stub
		
	}

}
