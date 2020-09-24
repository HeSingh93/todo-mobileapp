package com.example.todomobile.models;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class Expense implements Parcelable {

    private double amount;
    private String description;
    private int workOrderId;

    public Expense(double amount, String description, int workOrderId) {
        this.amount = amount;
        this.description = description;
        this.workOrderId = workOrderId;
    }


    protected Expense(Parcel in) {
        amount = in.readDouble();
        description = in.readString();
        workOrderId = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeDouble(amount);
        dest.writeString(description);
        dest.writeInt(workOrderId);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Expense> CREATOR = new Creator<Expense>() {
        @Override
        public Expense createFromParcel(Parcel in) {
            return new Expense(in);
        }

        @Override
        public Expense[] newArray(int size) {
            return new Expense[size];
        }
    };


    public double getAmount() {
        return amount;
    }

    public String getDescription() {
        return description;
    }

    public int getWorkOrderId() {
        return workOrderId;
    }

    @NonNull
    @Override
    public String toString() {
        return description + ", " + amount + "0 kr";
    }
}
