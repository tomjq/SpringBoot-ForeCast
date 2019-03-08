package com.weather.demo.domain;

import java.io.Serializable;

public class WeatherResponse implements Serializable{
	private Weather data;
	private String status;
	private String desc;
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public Weather getData() {
		return data;
	}
	public void setData(Weather data) {
		this.data = data;
	}


}
