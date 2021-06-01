package com.igorkunicyn.springdata.enums;

public enum SortProduct {

    SORT_MIN("min"),
    SORT_MAX("max"),
    SORT_MIN_AND_MAX("minAndMax");

    private final String name;

    SortProduct(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
