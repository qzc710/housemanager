package com.tn.utils;

public class HouseParam extends  PageParam{
    private String title;
    private Integer typeId;
    private Integer districtId;
    private Integer streetId;
    private String price;
    private String floorage;
    private Long startPrice;
    private Long endPrice;
    private Integer startFloorage;
    private Integer endFloorage ;


    public Integer getStreetId() {
        return streetId;
    }

    public void setStreetId(Integer streetId) {
        this.streetId = streetId;
    }

    public Long getStartPrice() {
        return startPrice;
    }

    public Long getEndPrice() {
        return endPrice;
    }

    public Integer getStartFloorage() {
        return startFloorage;
    }

    public Integer getEndFloorage() {
        return endFloorage;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public Integer getDistrictId() {
        return districtId;
    }

    public void setDistrictId(Integer districtId) {
        this.districtId = districtId;
    }

    public String getPrice() {

        return price;
    }

    public void setPrice(String price) {
        this.price = price;
        if (this.price != null && !this.price.equals("")) {
            String[] split = price.split("-");
            this.startPrice = Long.valueOf(split[0]);
            this.endPrice = Long.valueOf(split[1]);
        }
    }

    public String getFloorage() {
        return floorage;
    }

    public void setFloorage(String floorage) {
        this.floorage = floorage;
        if (this.floorage != null && !this.floorage.equals("")) {
            String[] split = floorage.split("-");
            this.startFloorage = Integer.valueOf(split[0]);
            this.endFloorage = Integer.valueOf(split[1]);
        }
    }
}
