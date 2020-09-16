package com.example.todomobile;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Adapter;
import android.widget.ListView;

import com.example.todomobile.models.WorkOrder;

import java.util.List;

public class OrderList extends AppCompatActivity {

    private List<WorkOrder> worksorders;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_list);

        Intent intent = getIntent();
        intent.setExtrasClassLoader(WorkOrder.class.getClassLoader());
        worksorders = intent.getParcelableArrayListExtra("WORKORDERS_LIST_MESSAGE");

        ListView orderListView = findViewById(R.id.listviewID);
        CustomListAdapter adapter = new CustomListAdapter(this, worksorders);
        orderListView.setAdapter(adapter);
    }
}