package com.example.todomobile.models;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class Expense implements Parcelable {

    private int id;
    private double amount;
    private String description;

    public Expense(int id, double amount, String description) {
        this.id = id;
        this.amount = amount;
        this.description = description;
    }

    protected Expense(Parcel in) {
        id = in.readInt();
        amount = in.readDouble();
        description = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeDouble(amount);
        dest.writeString(description);
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

    public int getId() {
        return id;
    }

    public double getAmount() {
        return amount;
    }

    public String getDescription() {
        return description;
    }

    @NonNull
    @Override
    public String toString() {
        return description + ", " + amount + "0 kr";
    }
}
