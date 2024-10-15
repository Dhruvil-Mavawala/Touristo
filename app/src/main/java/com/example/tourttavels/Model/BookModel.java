package com.example.tourttavels.Model;

import java.util.HashMap;

public class BookModel {
    public String gst;
    public String pack_id;
    public String pack_name;
    public String pack_pic;
    public String pack_price;
    public String  status;
    public String total_amount;
    public String username;

    public BookModel(String gst, String pack_id, String pack_name, String pack_pic, String pack_price, String status, String total_amount, String username) {
        this.gst = gst;
        this.pack_id = pack_id;
        this.pack_name = pack_name;
        this.pack_pic = pack_pic;
        this.pack_price = pack_price;
        this.status = status;
        this.total_amount = total_amount;
        this.username = username;
    }
    public BookModel() {

    }

    public String getGst() {
        return gst;
    }

    public void setGst(String gst) {
        this.gst = gst;
    }

    public String getPack_id() {
        return pack_id;
    }

    public void setPack_id(String pack_id) {
        this.pack_id = pack_id;
    }

    public String getPack_name() {
        return pack_name;
    }

    public void setPack_name(String pack_name) {
        this.pack_name = pack_name;
    }

    public String getPack_pic() {
        return pack_pic;
    }

    public void setPack_pic(String pack_pic) {
        this.pack_pic = pack_pic;
    }

    public String getPack_price() {
        return pack_price;
    }

    public void setPack_price(String pack_price) {
        this.pack_price = pack_price;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTotal_amount() {
        return total_amount;
    }

    public void setTotal_amount(String total_amount) {
        this.total_amount = total_amount;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public HashMap<String,Object> converttomap(BookModel model){
        HashMap<String,Object> map=new HashMap<>();

        map.put("gst",model.getGst());
        map.put("pack_id",model.getPack_id());
        map.put("pack_name",model.getPack_name());
        map.put("pack_pic",model.getPack_pic());
        map.put("pack_price",model.getPack_price());
        map.put("status",model.getStatus());
        map.put("total_amount",model.getTotal_amount());
        map.put("username",model.getUsername());

        return map;
    }
}


