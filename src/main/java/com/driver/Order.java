package com.driver;

public class Order {

    private String id;
    private int deliveryTime;

    public Order(String id, String deliveryTime) {

        // The deliveryTime has to converted from string to int and then stored in the attribute
        //deliveryTime  = HH*60 + MM
    	this.id=id;
    	String []times=deliveryTime.split(":");
    	int number=Integer.parseInt(times[0])*60;
    	this.deliveryTime=number+Integer.parseInt(times[1]);
    }

    public String getId() {
        return id;
    }

    public int getDeliveryTime() {return deliveryTime;}
}
