package com.driver.repository;

import com.driver.model.SubscriptionType;
import com.driver.model.WebSeries;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface WebSeriesRepository extends JpaRepository<WebSeries,Integer> {

    WebSeries findBySeriesName(String seriesName);

    @Query("select count(w) from WebSeries w where w.ageLimit <= :age and w.subscriptionType = 0")
    int getAvailableCountOfWebSeriesViewableWithBasic(@Param("age") int age);

    @Query("select count(w) from WebSeries w where w.ageLimit <= :age and w.subscriptionType != 2")
    int getAvailableCountOfWebSeriesViewableWithPro(@Param("age") int age);

    @Query("select count(w) from WebSeries w where w.ageLimit <= :age")
    int getAvailableCountOfWebSeriesViewableWithElite(@Param("age") int age);

    @Query("select w from WebSeries w where w.ageLimit <= :age")
    List<WebSeries> findByAgeLimit(@Param("age") int age);
}
