package io.github.anywhere.ui.utils;

/**
 * Created by PCPC on 2017-06-04.
 */

public class Data_Model {

    private String name;
    private String host;
    private String telnum;
    private int like_count;
    private double lat;
    private double lon;
    private double alt;

    public Data_Model(String name , String host , String telnum , int like_count , double lat ,double lon , double alt){
        this.name = name;
        this.host = host;
        this.telnum = telnum;
        this.like_count = like_count;
        this.lat =lat;
        this.lon = lon;
        this.alt = alt;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getTelnum() {
        return telnum;
    }

    public void setTelnum(String telnum) {
        this.telnum = telnum;
    }

    public int getLike_count() {
        return like_count;
    }

    public void setLike_count(int like_count) {
        this.like_count = like_count;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public double getAlt() {
        return alt;
    }

    public void setAlt(double alt) {
        this.alt = alt;
    }
}
