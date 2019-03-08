package com.weather.demo.config;


import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.weather.demo.Job.WeatherDataSycn;

@Configuration
public class QuartzConfiguration {
	 private static final int TIME = 1800;
	 //更新频率 //JobDetail 
	 @Bean
	 public JobDetail weatherDataSyncJobDetail() {
		 return JobBuilder.newJob(WeatherDataSycn.class).withIdentity("weatherDataSync") .storeDurably().build();
		 }
	 //Trigger触发器
	 //还可以使用CronScheduleBuilder来自定义cron表达式，更加灵活 
	 @Bean
	 public Trigger weatherDataSyncTrigger() {
		 SimpleScheduleBuilder schedBuilder = SimpleScheduleBuilder.simpleSchedule() .withIntervalInSeconds(TIME).repeatForever(); 
		 return TriggerBuilder.newTrigger().forJob(weatherDataSyncJobDetail()) .withIdentity("weatherDataSyncTrigger").withSchedule(schedBuilder).build(); 
		 }


}
