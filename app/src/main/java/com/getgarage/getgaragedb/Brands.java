package com.getgarage.getgaragedb;

/**
 * Created by Rajat on 23-10-2015.
 */
public class Brands {
    int brandId;

    String brandName;
    public Brands(String brandName){
        this.brandName=brandName;
    }

    public int getBrandId() {
        return brandId;
    }



    public String getBrandName() {
        return brandName;
    }

    public void setBrandId(int brandId) {
        this.brandId = brandId;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }
}
