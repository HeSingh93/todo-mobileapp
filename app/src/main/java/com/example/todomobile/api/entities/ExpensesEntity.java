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

    @SerializedName("workOrder")
    private WorkOrderEntity workOrderEntity;

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

    public WorkOrderEntity getWorkOrderEntity() {
        return workOrderEntity;
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

    public void setWorkOrderEntity(WorkOrderEntity workOrderEntity) {
        this.workOrderEntity = workOrderEntity;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

    }
}
