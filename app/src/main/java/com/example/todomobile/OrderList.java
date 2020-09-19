package com.example.todomobile;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.todomobile.models.Employee;
import com.example.todomobile.models.WorkOrder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class OrderList extends ToDoActivity {

    private ArrayList<WorkOrder> orderlistActiveEmployee;
    private ArrayList<WorkOrder> orderListNotAccepted;
    private ArrayList<WorkOrder> orderListAccepted;

    private Button buttonLogOut;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_list);

        final Intent intent = getIntent();
        intent.setExtrasClassLoader(WorkOrder.class.getClassLoader());
        orderlistActiveEmployee = intent.getParcelableArrayListExtra(WORKORDER_LIST_MESSAGE);

        buttonLogOut = findViewById(R.id.buttonOrderlistLogout);
        buttonLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(OrderList.this, Login.class));

            }
        });

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


        Collections.sort(orderListNotAccepted, new Comparator<WorkOrder>() {
            @Override
            public int compare(WorkOrder o1, WorkOrder o2) {
                return o1.getDateTimeInfo().compareTo(o2.getDateTimeInfo());
            }
        });

        Collections.sort(orderListAccepted, new Comparator<WorkOrder>() {
            @Override
            public int compare(WorkOrder o1, WorkOrder o2) {
                return o1.getDateTimeInfo().compareTo(o2.getDateTimeInfo());
            }
        });

        orderListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(OrderList.this, OrderDetail.class);
                intent.putExtra(CURRENT_WORKORDER_MESSAGE, (WorkOrder) parent.getItemAtPosition(position));
                intent.putParcelableArrayListExtra(WORKORDER_LIST_MESSAGE, orderlistActiveEmployee);
                startActivity(intent);
            }
        });

        orderListNotAcceptedView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
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