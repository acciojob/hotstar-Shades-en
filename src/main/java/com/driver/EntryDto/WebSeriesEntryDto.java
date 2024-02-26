package com.driver.EntryDto;

import com.driver.model.ProductionHouse;
import com.driver.model.SubscriptionType;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

public class WebSeriesEntryDto {


    private String seriesName;

    private int ageLimit;

    private double rating;

    private SubscriptionType subscriptionType; //This denotes with which of subscriptionType this webseries comes ie. BASIC,PRO, ELITE

    private Integer productionHouseId;

    public WebSeriesEntryDto(String seriesName, int ageLimit, double rating, SubscriptionType subscriptionType, Integer productionHouseId) {
        this.ageLimit = ageLimit;
        this.seriesName = seriesName;
        this.rating = rating;
        this.subscriptionType = subscriptionType;
        this.productionHouseId = productionHouseId;
    }

    public String getSeriesName() {
        return seriesName;
    }

    public void setSeriesName(String seriesName) {
        this.seriesName = seriesName;
    }

    public int getAgeLimit() {
        return ageLimit;
    }

    public void setAgeLimit(int ageLimit) {
        this.ageLimit = ageLimit;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public SubscriptionType getSubscriptionType() {
        return subscriptionType;
    }

    public void setSubscriptionType(SubscriptionType subscriptionType) {
        this.subscriptionType = subscriptionType;
    }

    public Integer getProductionHouseId() {
        return productionHouseId;
    }

    public void setProductionHouseId(Integer productionHouseId) {
        this.productionHouseId = productionHouseId;
    }
}
