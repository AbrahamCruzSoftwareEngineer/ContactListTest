package com.evolutiondso.www.contactlisttest.Model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Phone implements Parcelable {

    @SerializedName("work")
    @Expose
    private String work;
    @SerializedName("home")
    @Expose
    private String home;
    @SerializedName("mobile")
    @Expose
    private String mobile;

    public String getWork() {
        return work;
    }

    public void setWork(String work) {
        this.work = work;
    }

    public String getHome() {
        return home;
    }

    public void setHome(String home) {
        this.home = home;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }


    protected Phone(Parcel in) {
        work = in.readString();
        home = in.readString();
        mobile = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(work);
        dest.writeString(home);
        dest.writeString(mobile);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Phone> CREATOR = new Parcelable.Creator<Phone>() {
        @Override
        public Phone createFromParcel(Parcel in) {
            return new Phone(in);
        }

        @Override
        public Phone[] newArray(int size) {
            return new Phone[size];
        }
    };
}