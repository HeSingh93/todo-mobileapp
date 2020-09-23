package com.example.todomobile.api.entities;

import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.RequiresApi;

import com.google.gson.annotations.SerializedName;

public class LoginEntity implements Parcelable {

    @SerializedName("id")
    private Integer id;

    @SerializedName("username")
    private String userName;

    @SerializedName("password")
    private String password;

    @SerializedName("employee_id")
    private Integer employeeId;

    @SerializedName("is_admin")
    private boolean isAdmin;

    public LoginEntity() {
        //Empty no-args constructor
    }

    public LoginEntity(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    protected LoginEntity(Parcel in) {
        if (in.readByte() == 0) {
            id = null;
        } else {
            id = in.readInt();
        }
        userName = in.readString();
        password = in.readString();
        if (in.readByte() == 0) {
            employeeId = null;
        } else {
            employeeId = in.readInt();
        }
        isAdmin = in.readByte() != 0;
    }


    public static final Creator<LoginEntity> CREATOR = new Creator<LoginEntity>() {
        @Override
        public LoginEntity createFromParcel(Parcel in) {
            return new LoginEntity(in);
        }

        @Override
        public LoginEntity[] newArray(int size) {
            return new LoginEntity[size];
        }
    };

    // Getters
    public Integer getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public Integer getEmployeeId() {
        return employeeId;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    // Setters
    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @RequiresApi(api = Build.VERSION_CODES.Q)
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.userName);
        dest.writeString(this.password);
        dest.writeInt(this.employeeId);
        dest.writeBoolean(this.isAdmin);
    }
}
