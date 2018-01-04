package com.getgarage.getgaragedb;

/**
 * Created by Rajat on 23-10-2015.
 */
public class MyVehicles {
    int myVehicleId;
    String brand;
    String model;
    String platenumber;
    double mileage;
    public MyVehicles(int myVehicleId,String brand,String model, String platenumber, double mileage){
        this.myVehicleId=myVehicleId;
        this.brand=brand;
        this.model=model;
        this.platenumber=platenumber;
        this.mileage=mileage;
    }
    public MyVehicles(String brand,String model, String platenumber, double mileage){
        this.brand=brand;
        this.model=model;
        this.platenumber=platenumber;
        this.mileage=mileage;
    }
    public MyVehicles( String platenumber, double mileage){

        this.platenumber=platenumber;
        this.mileage=mileage;
    }

    public int getMyVehicleId() {
        return myVehicleId;
    }

    public void setMyVehicleId(int myVehicleId) {
        this.myVehicleId = myVehicleId;
    }

    public double getMileage() {
        return this.mileage;
    }

    public String getBrand() {
        return this.brand;
    }

    public String getModel() {
        return this.model;
    }

    public String getPlatenumber() {
        return this.platenumber;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setMileage(double mileage) {
        this.mileage = mileage;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setPlatenumber(String platenumber) {
        this.platenumber = platenumber;
    }
}
