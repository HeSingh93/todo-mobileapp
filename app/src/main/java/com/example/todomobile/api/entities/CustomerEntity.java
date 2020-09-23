package com.example.todomobile.api.entities;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class CustomerEntity implements Parcelable {

    @SerializedName("id")
    private Integer id;

    @SerializedName("company_name")
    private String companyName;

    @SerializedName("address")
    private String address;

    @SerializedName("contact_person")
    private String contactPerson;

    @SerializedName("telephone_no")
    private String telephoneNo;

    public CustomerEntity() {
        //Empty no-args constructor
    }

    // Getters
    public Integer getId() {
        return id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getAddress() {
        return address;
    }

    public String getContactPerson() {
        return contactPerson;
    }

    public String getTelephoneNo() {
        return telephoneNo;
    }

    // Setters
    public void setId(Integer id) {
        this.id = id;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setContactPerson(String contactPerson) {
        this.contactPerson = contactPerson;
    }

    public void setTelephoneNo(String telephoneNo) {
        this.telephoneNo = telephoneNo;
    }


    protected CustomerEntity(Parcel in) {
        if (in.readByte() == 0) {
            id = null;
        } else {
            id = in.readInt();
        }
        companyName = in.readString();
        address = in.readString();
        contactPerson = in.readString();
        telephoneNo = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (id == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(id);
        }
        dest.writeString(companyName);
        dest.writeString(address);
        dest.writeString(contactPerson);
        dest.writeString(telephoneNo);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<CustomerEntity> CREATOR = new Creator<CustomerEntity>() {
        @Override
        public CustomerEntity createFromParcel(Parcel in) {
            return new CustomerEntity(in);
        }

        @Override
        public CustomerEntity[] newArray(int size) {
            return new CustomerEntity[size];
        }
    };
}
