package com.example.utils.poi;

import java.util.Objects;

/**
 * @Description 国家信息
 * @Author blake
 * @Date 2019-01-25 11:46
 * @Version 1.0
 */
public class Country {

    private String name;

    private String shortCode;

    public Country() {
    }

    public Country(String name, String shortCode) {
        this.name = name;
        this.shortCode = shortCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShortCode() {
        return shortCode;
    }

    public void setShortCode(String shortCode) {
        this.shortCode = shortCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Country country = (Country) o;
        return Objects.equals(name, country.name) &&
                Objects.equals(shortCode, country.shortCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, shortCode);
    }
}
