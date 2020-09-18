package com.example.todomobile;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.todomobile.models.Employee;
import com.example.todomobile.models.WorkOrder;

import java.util.ArrayList;
import java.util.List;

public class OrderList extends ToDoActivity {

    private ArrayList<WorkOrder> orderlistActiveEmployee;
    private ArrayList<WorkOrder> orderListNotAccepted;
    private ArrayList<WorkOrder> orderListAccepted;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_list);

        final Intent intent = getIntent();
        intent.setExtrasClassLoader(WorkOrder.class.getClassLoader());
        orderlistActiveEmployee = intent.getParcelableArrayListExtra(WORKORDER_LIST_MESSAGE);

        orderListAccepted = new ArrayList<>();
        orderListNotAccepted = new ArrayList<>();

        for ( WorkOrder workOrder : orderlistActiveEmployee ) {
            if ( workOrder.getStatus() == STATUS_ASSIGNED ) {
                orderListNotAccepted.add(workOrder);
            } else if ( workOrder.getStatus() == STATUS_ACCEPTED ) {
                orderListAccepted.add(workOrder);
            }
        }

        final ListView orderListNotAcceptedView = findViewById(R.id.listviewNotAcceptedWorkorderID);
        CustomListAdapter adapterNotAccepted = new CustomListAdapter(this, orderListNotAccepted);
        orderListNotAcceptedView.setAdapter(adapterNotAccepted);

        final ListView orderListView = findViewById(R.id.listviewID);
        CustomListAdapter adapter = new CustomListAdapter(this, orderListAccepted);
        orderListView.setAdapter(adapter);

        orderListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(OrderList.this, OrderDetail.class);
                intent.putExtra(CURRENT_WORKORDER_MESSAGE, (WorkOrder) parent.getItemAtPosition(position));
                intent.putParcelableArrayListExtra(WORKORDER_LIST_MESSAGE, orderlistActiveEmployee);
                startActivity(intent);
            }
        });

        final ListView orderListViewAcceptedWorkOrder = findViewById(R.id.listviewNotAcceptedWorkorderID);
        //CustomListAdapter adapter = new CustomListAdapter(this, orderListAccepted);
        orderListView.setAdapter(adapter);

        orderListViewAcceptedWorkOrder.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(OrderList.this, OrderDetail.class);
                intent.putExtra(CURRENT_WORKORDER_MESSAGE, (WorkOrder) parent.getItemAtPosition(position));
                intent.putParcelableArrayListExtra(WORKORDER_LIST_MESSAGE, orderlistActiveEmployee);
                startActivity(intent);
            }
        });
    }
}