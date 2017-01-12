package com.example.sebastian.mockdata.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Sebastian on 2017-01-09.
 */
public class User implements Parcelable {

    private static String TAG = User.class.toString();

    private String name;
    private String lastName;
    private String country;

    public User(String name, String lastName, String country) {
        this.name = name;
        this.lastName = lastName;
        this.country = country;
    }

    protected User(Parcel in) {

        name = in.readString();
        lastName = in.readString();
        country = in.readString();
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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

        dest.writeString(name);
        dest.writeString(lastName);
        dest.writeString(country);
    }
}
