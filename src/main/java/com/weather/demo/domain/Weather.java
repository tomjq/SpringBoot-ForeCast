package com.weather.demo.domain;

import java.io.Serializable;
import java.util.List;

public class Weather implements Serializable{
	private Yesterday yesterday;
	private String city;
	private String ganmao;
	private String wendu;
	private List<ForeCast> forecast;
	public List<ForeCast> getForecast() {
		return forecast;
	}



	public void setForecast(List<ForeCast> forecast) {
		this.forecast = forecast;
	}



	public Yesterday getYesterday() {
		return yesterday;
	}
	public void setYesterday(Yesterday yesterday) {
		this.yesterday = yesterday;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getGanmao() {
		return ganmao;
	}
	public void setGanmao(String ganmao) {
		this.ganmao = ganmao;
	}
	public String getWendu() {
		return wendu;
	}
	public void setWendu(String wendu) {
		this.wendu = wendu;
	}
	

}
