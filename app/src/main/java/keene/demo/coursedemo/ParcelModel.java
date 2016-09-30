package keene.demo.coursedemo;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * this file belongs to package com.example.coursedemo of My Application
 * created at 15:07,07,2016
 *
 * @author keene
 * @version 1.0
 */
public class ParcelModel implements Parcelable {

    private String data;

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
         dest.writeString(data);
    }

    public static final Parcelable.Creator<ParcelModel> CREATOR=new Parcelable.Creator<ParcelModel>(){
        @Override
        public ParcelModel createFromParcel(Parcel source) {
            ParcelModel pm=new ParcelModel();
            pm.setData(source.readString());
            return pm;
        }

        @Override
        public ParcelModel[] newArray(int size) {
            return new ParcelModel[size];
        }
    };
}
