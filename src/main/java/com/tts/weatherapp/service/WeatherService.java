package com.tts.weatherapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.tts.weatherapp.model.Response;
import com.tts.weatherapp.model.Zip;
import com.tts.weatherapp.repository.ZipRepository;



@Service
public class WeatherService {
	
    @Value("${api_key}")
    private String apiKey;
    @Autowired
    private ZipRepository zipRepository;
    
    
    public Response getForecast(String zipCode) {
        String url = "http://api.openweathermap.org/data/2.5/weather?zip=" + 
            zipCode + "&units=imperial&appid=" + apiKey;
        RestTemplate restTemplate = new RestTemplate();      
        try {   
	    		Zip toSaveZip = new Zip();
	    		toSaveZip.setZipCode(zipCode);
	    		zipRepository.save(toSaveZip);        	
	            return restTemplate.getForObject(url, Response.class);
        }
        catch (HttpClientErrorException ex) {
            Response response = new Response();
            response.setName("error");
            return response;
        }
        
    }
    
    
    
}


