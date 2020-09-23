package com.example.todomobile.api.entities;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class EmployeeEntity implements Parcelable {

    @SerializedName("id")
    private Integer id;

    @SerializedName("first_name")
    private String firstName;

    @SerializedName("last_name")
    private String lastName;

    @SerializedName("telephone_no")
    private String telephoneNo;

    public EmployeeEntity() {
        //Empty no-args constructor
    }

    // Getters
    public Integer getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getTelephoneNo() {
        return telephoneNo;
    }

    // Setters
    public void setId(Integer id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setTelephoneNo(String telephoneNo) {
        this.telephoneNo = telephoneNo;
    }


    protected EmployeeEntity(Parcel in) {
        if (in.readByte() == 0) {
            id = null;
        } else {
            id = in.readInt();
        }
        firstName = in.readString();
        lastName = in.readString();
        telephoneNo = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (id == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(id);
        }
        dest.writeString(firstName);
        dest.writeString(lastName);
        dest.writeString(telephoneNo);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<EmployeeEntity> CREATOR = new Creator<EmployeeEntity>() {
        @Override
        public EmployeeEntity createFromParcel(Parcel in) {
            return new EmployeeEntity(in);
        }

        @Override
        public EmployeeEntity[] newArray(int size) {
            return new EmployeeEntity[size];
        }
    };
}
