package com.example.todomobile.api.entities;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class WorkOrderEntity implements Parcelable {

    @SerializedName("id")
    private Integer id;

    @SerializedName("date")
    private String date;

    @SerializedName("address")
    private String address;

    @SerializedName("work_description")
    private String workDescription;

    @SerializedName("contact_info")
    private String contactInfo;

    @SerializedName("customer_id")
    private Integer customerId;

    @SerializedName("employee_id")
    private Integer employeeId;

    @SerializedName("status")
    private Integer status;

    @SerializedName("work_started")
    private String workStarted;

    @SerializedName("work_finished")
    private String workFinished;

    @SerializedName("travel_hours")
    private Integer travelHours;

    @SerializedName("comment")
    private String comment;

    @SerializedName("time")
    private String time;

    public WorkOrderEntity() {
        //Empty no-args constructor
    }

    // Getters
    public Integer getId() {
        return id;
    }

    public String getDate() {
        return date;
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

    public Integer getCustomerId() {
        return customerId;
    }

    public Integer getEmployeeId() {
        return employeeId;
    }

    public Integer getStatus() {
        return status;
    }

    public String getWorkStarted() {
        return workStarted;
    }

    public String getWorkFinished() {
        return workFinished;
    }

    public Integer getTravelHours() {
        return travelHours;
    }

    public String getComment() {
        return comment;
    }

    public String getTime() {
        return time;
    }

    // Setters
    public void setId(Integer id) {
        this.id = id;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setWorkDescription(String workDescription) {
        this.workDescription = workDescription;
    }

    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public void setWorkStarted(String workStarted) {
        this.workStarted = workStarted;
    }

    public void setWorkFinished(String workFinished) {
        this.workFinished = workFinished;
    }

    public void setTravelHours(Integer travelHours) {
        this.travelHours = travelHours;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setTime(String time) {
        this.time = time;
    }

    protected WorkOrderEntity(Parcel in) {
        if (in.readByte() == 0) {
            id = null;
        } else {
            id = in.readInt();
        }
        date = in.readString();
        address = in.readString();
        workDescription = in.readString();
        contactInfo = in.readString();
        if (in.readByte() == 0) {
            customerId = null;
        } else {
            customerId = in.readInt();
        }
        if (in.readByte() == 0) {
            employeeId = null;
        } else {
            employeeId = in.readInt();
        }
        if (in.readByte() == 0) {
            status = null;
        } else {
            status = in.readInt();
        }
        workStarted = in.readString();
        workFinished = in.readString();
        if (in.readByte() == 0) {
            travelHours = null;
        } else {
            travelHours = in.readInt();
        }
        comment = in.readString();
        time = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (id == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(id);
        }
        dest.writeString(date);
        dest.writeString(address);
        dest.writeString(workDescription);
        dest.writeString(contactInfo);
        if (customerId == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(customerId);
        }
        if (employeeId == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(employeeId);
        }
        if (status == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(status);
        }
        dest.writeString(workStarted);
        dest.writeString(workFinished);
        if (travelHours == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(travelHours);
        }
        dest.writeString(comment);
        dest.writeString(time);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<WorkOrderEntity> CREATOR = new Creator<WorkOrderEntity>() {
        @Override
        public WorkOrderEntity createFromParcel(Parcel in) {
            return new WorkOrderEntity(in);
        }

        @Override
        public WorkOrderEntity[] newArray(int size) {
            return new WorkOrderEntity[size];
        }
    };
}