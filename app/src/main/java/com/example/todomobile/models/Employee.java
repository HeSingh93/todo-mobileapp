package com.example.todomobile.models;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class Employee implements Parcelable {

    private int id;
    private String firstName;
    private String lastName;
    private String telephoneNo;

    public Employee(int id, String firstName, String lastName, String telephoneNo) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.telephoneNo = telephoneNo;
    }

    protected Employee(Parcel in) {
        id = in.readInt();
        firstName = in.readString();
        lastName = in.readString();
        telephoneNo = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(firstName);
        dest.writeString(lastName);
        dest.writeString(telephoneNo);
    }

    public static final Creator<Employee> CREATOR = new Creator<Employee>() {
        @Override
        public Employee createFromParcel(Parcel in) {
            return new Employee(in);
        }

        @Override
        public Employee[] newArray(int size) {
            return new Employee[size];
        }
    };

    public int getId() {
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

    @NonNull
    @Override
    public String toString() {
        return "Name: " + firstName + " " + lastName + ", telephone no: " + telephoneNo;
    }

}
