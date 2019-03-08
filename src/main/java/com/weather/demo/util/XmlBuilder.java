package com.weather.demo.util;

import java.io.Reader;
import java.io.StringReader;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import com.weather.demo.domain.CityList;

public class XmlBuilder {

	public static Object xmlStrToObject(Class<CityList> class1, String string) throws Exception {
		Object xmlObject=null;
		Reader reader=null;
		JAXBContext context=JAXBContext.newInstance(class1);
		Unmarshaller unmarshaller = context.createUnmarshaller();
		reader=new StringReader(string);
		xmlObject=unmarshaller.unmarshal(reader);
		if(null!=reader){
			reader.close();
		}
		return xmlObject;
	}

}
