package io.github.anywhere.ui.utils;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by PCPC on 2017-06-06.
 */

public class DataModel implements Parcelable {

    String name;
    String address;
    String tel_num;
    int like_cnt;
    double lat;
    double lon;
    String logo;
    double altitude;
    boolean isliked = false;

    public String getLogo() {
        return logo;
    }

    public DataModel(String name , String address , String tel_num , int like_cnt , double lat , double lon , double altitude , String logo){
        this.name = name;
        this.address =address;
        this.tel_num = tel_num;
        this.like_cnt = like_cnt;
        this.lat = lat;
        this.lon = lon;
        this.altitude = altitude +10;
        this.logo = logo;

    }

    protected DataModel(Parcel in) {
        name = in.readString();
        address = in.readString();
        tel_num = in.readString();
        like_cnt = in.readInt();
        lat = in.readDouble();
        lon = in.readDouble();
        altitude = in.readDouble();
        logo = in.readString();
    }

    public static final Creator<DataModel> CREATOR = new Creator<DataModel>() {
        @Override
        public DataModel createFromParcel(Parcel in) {
            return new DataModel(in);
        }

        @Override
        public DataModel[] newArray(int size) {
            return new DataModel[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTel_num() {
        return tel_num;
    }

    public void setTel_num(String tel_num) {
        this.tel_num = tel_num;
    }

    public int getLike_cnt() {
        return like_cnt;
    }

    public void setLike_cnt(int like_cnt) {
        this.like_cnt = like_cnt;
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

    public double getAltitude() {
        return altitude;
    }

    public void setAltitude(double altitude) {
        this.altitude = altitude;
    }

    public void setIsliked(boolean isliked){
        this.isliked = isliked;
    }
    public boolean getIsliked(){
        return isliked;
    }
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(address);
        dest.writeString(tel_num);
        dest.writeInt(like_cnt);
        dest.writeDouble(lat);
        dest.writeDouble(lon);
        dest.writeDouble(altitude);
        dest.writeString(logo);
    }
}
