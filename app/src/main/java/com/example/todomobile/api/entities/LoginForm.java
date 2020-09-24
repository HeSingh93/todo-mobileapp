package com.example.todomobile.api.entities;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class LoginForm implements Parcelable {

    @SerializedName("username")
    private String userName;

    @SerializedName("password")
    private String password;

    @SerializedName("employeeEntity")
    private EmployeeEntity employeeEntity;

    public LoginForm(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    protected LoginForm(Parcel in) {
        userName = in.readString();
        password = in.readString();
    }


    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public EmployeeEntity getEmployeeEntity() {
        return employeeEntity;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmployeeEntity(EmployeeEntity employeeEntity) {
        this.employeeEntity = employeeEntity;
    }

    @Override
    public String toString() {
        return "LoginForm{" +
                "userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.userName);
        dest.writeString(this.password);
        dest.writeParcelable(this.employeeEntity, flags);

    }

    public static final Creator<LoginForm> CREATOR = new Creator<LoginForm>() {
        @Override
        public LoginForm createFromParcel(Parcel in) {
            return new LoginForm(in);
        }

        @Override
        public LoginForm[] newArray(int size) {
            return new LoginForm[size];
        }
    };
}
