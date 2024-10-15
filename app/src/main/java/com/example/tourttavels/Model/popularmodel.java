package com.example.tourttavels.Model;

import java.io.Serializable;

public class popularmodel implements Serializable {
    public String tittle;
    public String location;
    public String description;
    public String bed;
    public String guide;
    public String pic;
    public String wifi;
    public String duration;
    public String category;
    public boolean popular;
    public String price;
    public String score;

    public popularmodel() {

    }

    public popularmodel(String tittle, String location, String description, String bed, String guide, String pic, String wifi, String duration, String category, boolean popular, String price, String score) {
        this.tittle = tittle;
        this.location = location;
        this.description = description;
        this.bed = bed;
        this.guide = guide;
        this.pic = pic;
        this.wifi = wifi;
        this.duration = duration;
        this.category = category;
        this.popular = popular;
        this.price = price;
        this.score = score;
    }

    public boolean isPopular() {
        return popular;
    }

    public void setPopular(boolean popular) {
        this.popular = popular;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getTittle() {
        return tittle;
    }

    public void setTittle(String tittle) {
        this.tittle = tittle;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBed() {
        return bed;
    }

    public void setBed(String bed) {
        this.bed = bed;
    }

    public String getGuide() {
        return guide;
    }

    public void setGuide(String guide) {
        this.guide = guide;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getWifi() {
        return wifi;
    }

    public void setWifi(String wifi) {
        this.wifi = wifi;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }


    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
