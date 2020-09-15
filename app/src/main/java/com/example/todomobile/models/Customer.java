package com.example.todomobile.models;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class Customer implements Parcelable {

    private int id;
    private String companyName;
    private String address;
    private String contactPerson;
    private String telephoneNo;

    public Customer(int id, String companyName, String address, String contactPerson, String telephoneNo) {
        this.id = id;
        this.companyName = companyName;
        this.address = address;
        this.contactPerson = contactPerson;
        this.telephoneNo = telephoneNo;
    }

    protected Customer(Parcel in) {
        id = in.readInt();
        companyName = in.readString();
        address = in.readString();
        contactPerson = in.readString();
        telephoneNo = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(companyName);
        dest.writeString(address);
        dest.writeString(contactPerson);
        dest.writeString(telephoneNo);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Customer> CREATOR = new Creator<Customer>() {
        @Override
        public Customer createFromParcel(Parcel in) {
            return new Customer(in);
        }

        @Override
        public Customer[] newArray(int size) {
            return new Customer[size];
        }
    };

    public int getId() {
        return id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getAddress() {
        return address;
    }

    public String getContactPerson() {
        return contactPerson;
    }

    public String getTelephoneNo() {
        return telephoneNo;
    }

    @NonNull
    @Override
    public String toString() {
        return companyName + ", " + address + "\nContact person: " + contactPerson + ", " + telephoneNo;
    }
}
