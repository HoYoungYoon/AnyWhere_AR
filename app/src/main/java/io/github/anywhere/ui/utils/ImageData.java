package io.github.anywhere.ui.utils;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by PCPC on 2017-06-06.
 */

public class ImageData implements Parcelable {
    private String name;
    private int view_cnt;

    private int null_cnt = 0;
    private String [] ImgName = new String[7];
    public ImageData(){
    }
    public ImageData(String name ,int view_cnt  , String [] ImgName){
        this.name = name;
        this.view_cnt = view_cnt;
        this.ImgName = ImgName;

        for(int i=0; i<getImgName().length; i++) {
            if (getImgName()[i].contains("jpg"))
                null_cnt++;
        }

    }

    protected ImageData(Parcel in) {
        name = in.readString();
        view_cnt = in.readInt();
        null_cnt = in.readInt();
        ImgName = in.createStringArray();
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getView_cnt() {
        return view_cnt;
    }

    public void setView_cnt(int view_cnt) {
        this.view_cnt = view_cnt;
    }

    public int getNull_cnt(){

        return null_cnt;
    }
    public String[] getImgName(){
        return ImgName;
    }

    public void setImgName(String[] imgName) {
        ImgName = imgName;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeString(name);
        dest.writeInt(view_cnt);
        dest.writeInt(null_cnt);
        dest.writeStringArray(ImgName);
    }

    @SuppressWarnings("rawtypes")
    public static final Creator CREATOR = new Creator() {

        @Override
        public ImageData createFromParcel(Parcel in) {
            return new ImageData(in);
        }

        @Override
        public ImageData[] newArray(int size) {
            // TODO Auto-generated method stub
            return new ImageData[size];
        }

    };


}
