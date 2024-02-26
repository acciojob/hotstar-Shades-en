package com.driver.controllers;


import com.driver.EntryDto.WebSeriesEntryDto;
import com.driver.model.WebSeries;
import com.driver.services.WebSeriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/webseries")
public class WebseriesController {

    @Autowired
    WebSeriesService webSeriesService;

    @PostMapping("/add")
    public int addWebSeries(WebSeriesEntryDto webSeriesEntryDto){

        try{
            return webSeriesService.addWebSeries(webSeriesEntryDto);

        }catch (Exception e){
            return -1;
        }
    }

}
