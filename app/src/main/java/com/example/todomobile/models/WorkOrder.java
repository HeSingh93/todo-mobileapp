package com.example.todomobile.models;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

public class WorkOrder implements Parcelable {

    private int id;
    private String dateTimeInfo;
    private String address;
    private String workDescription;
    private String contactInfo;
    private Customer customer;
    private int status;
    private String timeStarted;
    private String timeFinished;
    private double travelHours;
    private String comment;
    private List<Expense> expenses;

    public WorkOrder(int id, String dateTimeInfo, String address, String workDescription, String contactInfo,
                     Customer customer, int status) {
        this.id = id;
        this.dateTimeInfo = dateTimeInfo;
        this.address = address;
        this.workDescription = workDescription;
        this.contactInfo = contactInfo;
        this.customer = customer;
        this.status = status;
        this.timeStarted = "";
        this.timeFinished = "";
        this.travelHours = 0.0;
        this.comment = "";
        this.expenses = new ArrayList<>();
    }

    protected WorkOrder(Parcel in) {
        id = in.readInt();
        dateTimeInfo = in.readString();
        address = in.readString();
        workDescription = in.readString();
        contactInfo = in.readString();
        customer = in.readParcelable(Customer.class.getClassLoader());
        status = in.readInt();
        timeStarted = in.readString();
        timeFinished = in.readString();
        travelHours = in.readDouble();
        comment = in.readString();
        expenses = in.createTypedArrayList(Expense.CREATOR);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(dateTimeInfo);
        dest.writeString(address);
        dest.writeString(workDescription);
        dest.writeString(contactInfo);
        dest.writeParcelable(customer, flags);
        dest.writeInt(status);
        dest.writeString(timeStarted);
        dest.writeString(timeFinished);
        dest.writeDouble(travelHours);
        dest.writeString(comment);
        dest.writeTypedList(expenses);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<WorkOrder> CREATOR = new Creator<WorkOrder>() {
        @Override
        public WorkOrder createFromParcel(Parcel in) {
            return new WorkOrder(in);
        }

        @Override
        public WorkOrder[] newArray(int size) {
            return new WorkOrder[size];
        }
    };

    public int getId() {
        return id;
    }

    public String getDateTimeInfo() {
        return dateTimeInfo;
    }

    public String getAddress() {
        return address;
    }

    public String getWorkDescription() {
        return workDescription;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public Customer getCustomer() {
        return customer;
    }

    public int getStatus() {
        return status;
    }

    public String getTimeStarted() {
        return timeStarted;
    }

    public String getTimeFinished() {
        return timeFinished;
    }

    public double getTravelHours() {
        return travelHours;
    }

    public String getComment() {
        return comment;
    }

    public List<Expense> getExpenses() {
        return expenses;
    }

    @NonNull
    @Override
    public String toString() {
        return "Workorder no " + id + " Time: " + dateTimeInfo
                + "\nCustomer: " + customer.getCompanyName() + " Address: " + address
                + "\nContact info: " + contactInfo
                + "\nWork description: " + workDescription;
    }
}
