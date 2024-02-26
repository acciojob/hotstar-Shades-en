package com.driver.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table
public class ProductionHouse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique = true)
    private String name;

    private double ratings;

    @OneToMany(mappedBy = "productionHouse",cascade = CascadeType.ALL)
    private List<WebSeries> webSeriesList;

    public ProductionHouse(String name) {
        this.name = name;
        this.webSeriesList = new ArrayList<>();
    }

    public ProductionHouse() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<WebSeries> getWebSeriesList() {
        return webSeriesList;
    }

    public void setWebSeriesList(List<WebSeries> webSeriesList) {
        this.webSeriesList = webSeriesList;
    }

    public double getRatings() {
        return ratings;
    }

    public void setRatings(double ratings) {
        this.ratings = ratings;
    }
}
