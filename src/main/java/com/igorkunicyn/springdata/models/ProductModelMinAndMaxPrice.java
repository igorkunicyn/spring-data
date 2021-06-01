package com.igorkunicyn.springdata.models;

import lombok.Data;

@Data
public class ProductModelMinAndMaxPrice {

    private long idMin;
    private long idMax;
    private String title;
    private int priceMin;
    private int priceMax;


}
