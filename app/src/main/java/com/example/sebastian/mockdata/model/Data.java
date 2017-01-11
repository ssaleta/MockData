package com.example.sebastian.mockdata.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Sebastian on 2017-01-09.
 */
public class Data implements Parcelable {

    private static String TAG = Data.class.toString();

    private String title;
    private String name;
    private String surname;
    private String country;


    public Data (String name, String surname, String country){
        this.name = name;
        this.surname = surname;
        this.country = country;
    }

    protected Data(Parcel in) {
        title = in.readString();
        name = in.readString();
        surname = in.readString();
        country = in.readString();
    }

    public static final Creator<Data> CREATOR = new Creator<Data>() {
        @Override
        public Data createFromParcel(Parcel in) {
            return new Data(in);
        }

        @Override
        public Data[] newArray(int size) {
            return new Data[size];
        }
    };

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(name);
        dest.writeString(surname);
        dest.writeString(country);
    }
}
