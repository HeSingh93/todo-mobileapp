package com.example.todomobile.models;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class WorkOrder implements Parcelable {

    private int id;
    private String dateTimeInfo;
    private String address;
    private String workDescription;
    private String contactInfo;
    private Customer customer;
    private Employee employee;
    private int status;
    private String timeStarted;
    private String timeFinished;
    private double travelHours;
    private String comment;
    private List<Expense> expenses;

    public WorkOrder(int id, String dateTimeInfo, String address, String workDescription, String contactInfo,
                     Customer customer, Employee employee, int status) {
        this.id = id;
        this.dateTimeInfo = dateTimeInfo;
        this.address = address;
        this.workDescription = workDescription;
        this.contactInfo = contactInfo;
        this.customer = customer;
        this.employee = employee;
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
        employee = in.readParcelable(Employee.class.getClassLoader());
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
        dest.writeParcelable(employee, flags);
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

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getTimeStarted() {
        return timeStarted;
    }

    public void setTimeStarted(String timeStarted) {
        this.timeStarted = timeStarted;
    }

    public String getTimeFinished() {
        return timeFinished;
    }

    public void setTimeFinished(String timeFinished) {
        this.timeFinished = timeFinished;
    }

    public double getTravelHours() {
        return travelHours;
    }

    public void setTravelHours(double travelHours) {
        this.travelHours = travelHours;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public List<Expense> getExpenses() {
        return expenses;
    }

    public void addExpense(Expense expense) {
        expenses.add(expense);
    }

    @NonNull
    @Override
    public String toString() {
        return "Workorder no " + id + " Time: " + dateTimeInfo
                + "\nCustomer: " + customer.getCompanyName() + " Address: " + address
                + "\nContact info: " + contactInfo
                + "\nWork description: " + workDescription
                + "\nNumber of expenses: " + expenses.size();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WorkOrder workOrder = (WorkOrder) o;
        return id == workOrder.id &&
                dateTimeInfo.equals(workOrder.dateTimeInfo) &&
                address.equals(workOrder.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, dateTimeInfo, address);
    }
}
