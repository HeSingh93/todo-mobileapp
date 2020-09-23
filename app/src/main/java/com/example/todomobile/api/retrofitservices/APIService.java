package com.example.todomobile.api.retrofitservices;

import com.example.todomobile.api.entities.LoginEntity;
import com.example.todomobile.api.entities.WorkOrderEntity;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface APIService {

    @GET("/api/workorders/")
    Call<List<WorkOrderEntity>> getCurrentWorkOrders();

    @GET("/api/workorders/{employeeid}")
    Call<List<WorkOrderEntity>> getSpecificWorkOrder(
            @Path("id") long id
    );

    @POST("api/login/")
    Call<LoginEntity> login(
            @Body LoginEntity login
    );

}
