package com.example.todomobile.api.retrofitservices;

import com.example.todomobile.api.entities.WorkOrderEntity;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface APIService {

    @GET("/api/workorders/")
    Call<List<WorkOrderEntity>> getCurrentWorkOrders();

}
