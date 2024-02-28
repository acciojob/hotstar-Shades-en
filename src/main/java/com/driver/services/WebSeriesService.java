package com.driver.services;

import com.driver.EntryDto.WebSeriesEntryDto;
import com.driver.exception.ProductionHouseNotFoundException;
import com.driver.exception.SeriesAlreadyExistsException;
import com.driver.model.ProductionHouse;
import com.driver.model.WebSeries;
import com.driver.repository.ProductionHouseRepository;
import com.driver.repository.WebSeriesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class WebSeriesService {

    @Autowired
    WebSeriesRepository webSeriesRepository;

    @Autowired
    ProductionHouseRepository productionHouseRepository;

    public Integer addWebSeries(WebSeriesEntryDto webSeriesEntryDto) throws  Exception{

        //Add a webSeries to the database and update the ratings of the productionHouse
        //Incase the seriesName is already present in the Db throw Exception("Series is already present")
        //use function written in Repository Layer for the same
        //Dont forget to save the production and webseries Repo

        WebSeries webSeries = webSeriesRepository.findBySeriesName(webSeriesEntryDto.getSeriesName());
        if(webSeries != null){
            throw new SeriesAlreadyExistsException("Series is already present");
        }

        Optional<ProductionHouse> productionHouseOptional = productionHouseRepository.findById(webSeriesEntryDto.getProductionHouseId());
        if(!productionHouseOptional.isPresent()){
            throw new ProductionHouseNotFoundException("Invalid ProductionHouse Id");
        }

        ProductionHouse productionHouse = productionHouseOptional.get();
        webSeries = new WebSeries(
            webSeriesEntryDto.getSeriesName(),
            webSeriesEntryDto.getAgeLimit(),
            webSeriesEntryDto.getRating(),
            webSeriesEntryDto.getSubscriptionType()
        );

        productionHouse.getWebSeriesList().add(webSeries);
        webSeries.setProductionHouse(productionHouse);
        double avgRatings = 0;
        for(WebSeries webSeries1: productionHouse.getWebSeriesList()){
            avgRatings += webSeries1.getRating();
        }
        avgRatings /= productionHouse.getWebSeriesList().size();
        productionHouse.setRatings(avgRatings);
        ProductionHouse savedProductionHouse = productionHouseRepository.save(productionHouse);
        return savedProductionHouse.getWebSeriesList().get(savedProductionHouse.getWebSeriesList().size()-1).getId();
    }

}
