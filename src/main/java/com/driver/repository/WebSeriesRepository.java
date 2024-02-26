package com.driver.repository;

import com.driver.model.WebSeries;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WebSeriesRepository extends JpaRepository<WebSeries,Integer> {

    WebSeries findBySeriesName(String seriesName);
}
