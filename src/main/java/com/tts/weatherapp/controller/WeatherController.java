package com.tts.weatherapp.controller;



import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.tts.weatherapp.model.Request;
import com.tts.weatherapp.model.Response;
import com.tts.weatherapp.model.Zip;
import com.tts.weatherapp.repository.ZipRepository;
import com.tts.weatherapp.service.WeatherService;

@Controller
public class WeatherController {
	
    @Autowired
    private WeatherService weatherService;
    
    @Autowired
    private ZipRepository zipRepository;

    public List<Zip> getZipList() {
    	List<Zip> convertZip = new ArrayList<Zip>();
    	List<Zip> zips = zipRepository.findAll();
    	for (int i = 0; i < zips.size() && i < 10; i++) {
    		convertZip.add(zips.get(i));
    	}
    	return convertZip;
    	
    }
    
    @GetMapping("/")
    public String getIndex(Model model) {
        model.addAttribute("request", new Request());  
        return "index";
    }
    
    @PostMapping("/")
    public String postIndex(Request request, Model model) {
        Response data = weatherService.getForecast(request.getZipCode());
        List<Zip> zips = getZipList();
        model.addAttribute("data", data);
        model.addAttribute("zips", zips);
        return "index";
    }
}
