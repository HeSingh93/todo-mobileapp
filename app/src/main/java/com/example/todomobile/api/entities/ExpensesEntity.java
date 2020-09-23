package com.example.todomobile.api.entities;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class ExpensesEntity implements Parcelable {

    @SerializedName("id")
    private Integer id;

    @SerializedName("amount")
    private double amount;

    @SerializedName("description")
    private String description;

    @SerializedName("work_order_id")
    private Integer workOrderId;

    public ExpensesEntity() {
        // No-args constructor
    }

    //Getters
    public Integer getId() {
        return id;
    }

    public double getAmount() {
        return amount;
    }

    public String getDescription() {
        return description;
    }

    public Integer getWorkOrderId() {
        return workOrderId;
    }

    //Setters
    public void setId(Integer id) {
        this.id = id;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setWorkOrderId(Integer workOrderId) {
        this.workOrderId = workOrderId;
    }

    protected ExpensesEntity(Parcel in) {
        if (in.readByte() == 0) {
            id = null;
        } else {
            id = in.readInt();
        }
        amount = in.readDouble();
        description = in.readString();
        if (in.readByte() == 0) {
            workOrderId = null;
        } else {
            workOrderId = in.readInt();
        }
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (id == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(id);
        }
        dest.writeDouble(amount);
        dest.writeString(description);
        if (workOrderId == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(workOrderId);
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<ExpensesEntity> CREATOR = new Creator<ExpensesEntity>() {
        @Override
        public ExpensesEntity createFromParcel(Parcel in) {
            return new ExpensesEntity(in);
        }

        @Override
        public ExpensesEntity[] newArray(int size) {
            return new ExpensesEntity[size];
        }
    };

}
