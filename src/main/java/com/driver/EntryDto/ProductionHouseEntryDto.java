package com.driver.EntryDto;

public class ProductionHouseEntryDto {

    private String name;

    public ProductionHouseEntryDto(String name) {
        this.name = name;
    }

    public ProductionHouseEntryDto(){}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
